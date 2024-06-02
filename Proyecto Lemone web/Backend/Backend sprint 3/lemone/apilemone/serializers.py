from rest_framework import serializers
from authentication.models import CustomUser
from .models import Bodega, Categoria, Operacion, Orden, Producto, ProductoDestacado, PuntoClave, PuntoClavePorProducto, TipoDeVino


class CategoriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Categoria
        fields = '__all__'

class TipoDeVinoSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoDeVino
        fields = '__all__'
  
class BodegaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Bodega
        fields = '__all__'
 
class PuntoClaveSerializer(serializers.ModelSerializer):
    class Meta:
        model = PuntoClave
        fields = '__all__'
       
class ProductoSerializer(serializers.ModelSerializer):
    categoria = CategoriaSerializer()
    tipodevino = TipoDeVinoSerializer()
    bodega = BodegaSerializer()
    class Meta:
        model = Producto
        fields = '__all__'

class PuntoClavePorProductoSerializer(serializers.ModelSerializer):
    class Meta:
        model = PuntoClavePorProducto
        fields = '__all__'


class ProductoDestacadoSerializer(serializers.ModelSerializer):
    producto = ProductoSerializer()
    class Meta:
        model = ProductoDestacado
        fields = '__all__'


class OrdenSerializer(serializers.ModelSerializer):
    class Meta:
        model = Orden
        fields = '__all__'
        

class OperacionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Operacion
        fields = '__all__'

class UsuarioSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomUser
        fields = '__all__'





