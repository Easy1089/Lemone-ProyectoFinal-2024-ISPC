from rest_framework import serializers
from authentication.models import CustomUser
from .models import Categoria, Operacion, Orden, Producto, ProductoDestacado


class ProductoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producto
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
        
class CategoriaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Categoria
        fields = '__all__'

class OperacionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Operacion
        fields = '__all__'

class UsuarioSerializer(serializers.ModelSerializer):
    class Meta:
        model = CustomUser
        fields = '__all__'



