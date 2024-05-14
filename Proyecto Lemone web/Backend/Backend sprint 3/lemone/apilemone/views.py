from datetime import date
from decimal import Decimal
from rest_framework.views import APIView
from rest_framework.response import Response
from authentication.models import CustomUser
from .models import Bodega, Categoria, Operacion, Orden, Producto, PuntoClave, PuntoClavePorProducto, TipoDeOperacion, ProductoDestacado, TipoDeVino
from .serializers import CategoriaSerializer, OperacionSerializer, OrdenSerializer, ProductoDestacadoSerializer, ProductoSerializer, PuntoClavePorProductoSerializer, PuntoClaveSerializer, UsuarioSerializer
from django.views import View
from django.http import JsonResponse
from django.db.models import Sum, F, DecimalField, ExpressionWrapper,  Value, IntegerField
from django.db.models.functions import Cast

from rest_framework.views import APIView
from rest_framework.response import Response
from .models import PuntoClavePorProducto
from .serializers import PuntoClavePorProductoSerializer

from rest_framework.views import APIView
from rest_framework.response import Response
from collections import defaultdict
from .models import PuntoClavePorProducto
from .serializers import PuntoClavePorProductoSerializer
from rest_framework import status

class PuntosClavesPorProductoView(APIView):
    def get(self, request, producto_id=None):
        puntos_claves_por_producto = defaultdict(list)

        if producto_id is None:
            puntos_claves = PuntoClavePorProducto.objects.all()
        else:
            puntos_claves = PuntoClavePorProducto.objects.filter(producto_id=producto_id)

        for punto_clave in puntos_claves:
            puntos_claves_por_producto[punto_clave.producto_id].append(punto_clave.puntoclave.nombre)

        response_data = []
        for producto_id, nombres_puntos_claves in puntos_claves_por_producto.items():
            producto_puntos_claves = {
                "producto": producto_id,
                "puntosclaves": [{"nombre": nombre} for nombre in nombres_puntos_claves]
            }
            response_data.append(producto_puntos_claves)

        datos = {
            'message': 'Success',
            'puntosclavesporproducto': response_data
            }
           
        return Response(datos)    
 
class ProductoView(APIView):
    def get(self, request, producto_id=None):
        if producto_id is None:
            # Obtener todos los productos
            productos = Producto.objects.all()
            serializer = ProductoSerializer(productos, many=True)
            if len(serializer.data) > 0:
                datos = {'message': 'Success', 'productos': serializer.data}
            else:
                datos = {'message': 'Productos no encontrados...'}
        else:
            # Obtener un producto específico por ID
            try:
                producto = Producto.objects.get(id=producto_id)
                serializer = ProductoSerializer(producto)
                datos = {'message': 'Success', 'producto': serializer.data}
            except Producto.DoesNotExist:
                datos = {'message': 'Producto no encontrado...'}
        return Response(datos)

    def post(self, request):
        print("Llegó a la vista POST de productos")  
        # Obtener los datos del producto del cuerpo de la solicitud JSON
        datos = request.data
        datos_producto = datos.get('producto')
        usuario = datos.get('usuario')

        try:
            categoria = Categoria.objects.get(id=datos_producto.get('categoria'))
        except Categoria.DoesNotExist:
            print("Categoría no encontrada") 
            datos = {'message': 'Categoría no encontrada...'}
            return Response(datos, status=status.HTTP_404_NOT_FOUND)
        
        # Obtener la instancia de CustomUser correspondiente al nombre de usuario proporcionado
        usuarioalta = CustomUser.objects.get(id=usuario['id'])

        # Crear el producto en la base de datos
        producto = Producto.objects.create(
            codigo=datos_producto['codigo'],
            nombre=datos_producto['nombre'],
            descripcion=datos_producto['descripcion'],
            inventariominimo=datos_producto['inventariominimo'],
            preciodecosto=datos_producto['preciodecosto'],
            preciodeventa=datos_producto['preciodeventa'],
            categoria=categoria,
            activoactualmente=datos_producto['activoactualmente'],
            imagen=datos_producto['imagen'],
            estado=datos_producto['estado'],
            usuarioalta=usuarioalta,
            fechaalta=date.today(),
            usuariomodificacion=usuarioalta,
            fehamodificacion=date.today()      
        )
        # Devolver una respuesta exitosa
        return Response(datos_producto)

    def put(self, request, producto_id=None):
        print("Llegó a la vista put de productos")  
        
        datos = request.data
        print("Request.data: ", request)
        datos_producto = request.data.get('producto')
        print("Request.data - producto: ", datos_producto)
        
        try:
            producto = Producto.objects.get(id=datos_producto.get('id'))            
        except Producto.DoesNotExist:
            print("Producto no encontrado") 
            datos = {'message': 'Producto no encontrado...'}
            return Response(datos, status=status.HTTP_404_NOT_FOUND)
        
        try:
            categoria = Categoria.objects.get(id=datos_producto.get('categoria'))
        except Categoria.DoesNotExist:
            print("Categoría no encontrada") 
            datos = {'message': 'Categoría no encontrada...'}
            return Response(datos, status=status.HTTP_404_NOT_FOUND)


        usuario = datos.get('usuario')
        usuariomodificacion = CustomUser.objects.get(id=usuario['id'])
                       
        producto.id = datos_producto.get('id')
        producto.codigo = datos_producto.get('codigo')
        producto.nombre = datos_producto.get('nombre')
        producto.descripcion = datos_producto.get('descripcion')
        producto.inventariominimo = datos_producto.get('inventariominimo')
        producto.preciodecosto = datos_producto.get('preciodecosto')
        producto.preciodeventa = datos_producto.get('preciodeventa')
        producto.categoria = categoria
        producto.activoactualmente = datos_producto.get('activoactualmente')
        producto.imagen = datos_producto.get('imagen')
        producto.usuariomodificacion = usuariomodificacion
        producto.fechamodificacion = date.today()
        
        producto.save()

        serializer = ProductoSerializer(producto)
        datos = {'message': 'Producto actualizado exitosamente',
                'producto': serializer.data}
        return Response(datos, status=status.HTTP_200_OK)


    def delete(self, request, producto_id):
        try:
            producto = Producto.objects.get(id=producto_id)
            producto.delete()
            return Response({'message': 'Producto eliminado exitósamente'}, status=status.HTTP_204_NO_CONTENT)
        except Producto.DoesNotExist:
            return Response({'message': 'Producto no encontrado'}, status=status.HTTP_404_NOT_FOUND)
        except Exception as e:
            return Response({'message': str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)


class ProductoDestacadoView(APIView):
    def get(self, request, productodestacado_id=None):
        if productodestacado_id is None:
            productosdestacados = ProductoDestacado.objects.all()
            serializer = ProductoDestacadoSerializer(productosdestacados, many=True)
            if len(serializer.data) > 0:
                datos = {'message': 'Success', 'productosdestacados': serializer.data}
            else:
                datos = {'message': 'Productos destacados no encontrados...'}
        else:
            try:
                productodestacado = ProductoDestacado.objects.get(id=productodestacado_id)
                serializer = ProductoDestacadoSerializer(productodestacado)
                datos = {'message': 'Success', 'producto destacado': serializer.data}
            except Producto.DoesNotExist:
                datos = {'message': 'Producto destacado no encontrado...'}
        return Response(datos)
    
    
class UsuariosView(APIView):
     def get(self, request, usuario_id=None):
        if usuario_id is None:
            # Obtener todos los usuarios
            usuarios = CustomUser.objects.all()
            serializer = UsuarioSerializer(usuarios, many=True)
            if len(serializer.data) > 0:
                datos = {'message': 'Success', 'usuarios': serializer.data}
            else:
                datos = {'message': 'Usuarios no encontrados...'}
        else:
            # Obtener un Usuario específico por ID
            try:
                usuario = CustomUser.objects.get(id=usuario_id)
                serializer = UsuarioSerializer(usuario)
                datos = {'message': 'Success', 'usuario': serializer.data}
            except Producto.DoesNotExist:
                datos = {'message': 'Usuario no encontrado...'}
        return Response(datos)


class CategoriaView(APIView):
    def get(self, request):
        categorias = Categoria.objects.all()
        serializer = CategoriaSerializer(categorias, many=True)
        if len(serializer.data) > 0:
            datos = {'message': 'Success', 'categorias': serializer.data}
        else:
            datos = {'message': 'Categorías no encontradas...'}
        return Response(datos)
    
class PuntosClavesView(APIView):
    def get(self, request):
        puntosclaves = PuntoClave.objects.all()
        serializer = PuntoClaveSerializer(puntosclaves, many=True)
        if len(serializer.data) > 0:
            datos = {'message': 'Success', 'puntosclave': serializer.data}
        else:
            datos = {'message': 'Puntos clave no encontrados...'}
        return Response(datos)
    
class OrdenView(APIView):
    def get(self, request):
        ordenes = Orden.objects.all()
        serializer = OrdenSerializer(ordenes, many=True)
        if len(serializer.data) > 0:
            datos = {'message': 'Success', 'ordenes': serializer.data}
        else:
            datos = {'message': 'Órdenes no encontradas...'}
        return Response(datos)

class OperacionView(APIView):
    def get(self, request):
        operaciones = Operacion.objects.all()
        serializer = OperacionSerializer(operaciones, many=True)
        if len(serializer.data) > 0:
            datos = {'message': 'Success', 'operaciones': serializer.data}
        else:
            datos = {'message': 'Operaciones no encontradas...'}
        return Response(datos)

class ComprasView(APIView):
    def get(self, request):
        # Filtra las operaciones que representan compras
        operaciones_compras = Operacion.objects.filter(tipodeoperacion__nombre='Compra')

        # Calcula el total de precios multiplicando el precio del producto por la cantidad en cada operación
        suma_precios = operaciones_compras.aggregate(
            total_precio=Sum(ExpressionWrapper(F('producto__preciodecosto') * F('cantidad'), output_field=DecimalField())))

        # Serializa las operaciones para respuesta detallada
        serializer = OperacionSerializer(operaciones_compras, many=True)

        datos = {
            'message': 'Success',
            'total_precio_compras': suma_precios['total_precio'] or 0,
            'operaciones_compras': serializer.data
        }
           
        return Response(datos)
    
class CantidadDeUsuariosView(APIView):
    def get(self, request):
        cantidad_usuarios = CustomUser.objects.filter(is_active=True, is_superuser=False).count()

        datos = {
            'message': 'Success',
            'cantidad_usuarios_activos': cantidad_usuarios
        }
           
        return Response(datos)    
    

class CantidadProductosView(APIView):
    def get(self, request):
        #Cantidad de productos activos
        productos_activos = Producto.objects.filter(activoactualmente=True).count()

        #Productos vendidos
        #cantidad_ventas = Operacion.objects.filter(tipodeoperacion__nombre='Venta').aggregate(total_ventas=Sum('cantidad'))

        #Productos comprados
        #cantidad_compras = Operacion.objects.filter(tipodeoperacion__nombre='Compra').aggregate(total_compras=Sum('cantidad'))

        #Total
        cantidad_productos = productos_activos #- (cantidad_ventas['total_ventas'] or 0) + (cantidad_compras['total_compras'] or 0)

        datos = {
            'message': 'Success',
            'cantidad_productos': cantidad_productos
        }
           
        return Response(datos)

        
class SumaVentasView(APIView):
    def get(self, request):
        suma_ventas = Orden.objects.filter(estadodeorden__nombre='Finalizada').aggregate(total_ventas=Sum('importetotal'))

        datos = {
            'message': 'Success',
            'total_ventas': suma_ventas['total_ventas'] or 0   
        }
           
        return Response(datos)