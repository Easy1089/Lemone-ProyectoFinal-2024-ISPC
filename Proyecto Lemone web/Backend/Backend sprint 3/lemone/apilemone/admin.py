from django.contrib import admin

# Register your models here.
from .models import Producto, ProductoDestacado
from .models import Categoria
from .models import TipoDeEnvio
from .models import TipoDeOperacion
from .models import TipoDePersona
from .models import EstadoDeOrden
from .models import MedioDePago
from .models import Persona
from .models import Orden
from .models import OrdenDetalle
from .models import Operacion
from .models import Bodega
from .models import PuntoClave
from .models import PuntoClavePorProducto
from .models import TipoDeVino

class CategoriaAdmin(admin.ModelAdmin):
    list_display = ('nombre', 'descripcion', 'activoactualmente')


class ProductoAdmin(admin.ModelAdmin):
    list_display = ('id', 'codigo', 'nombre', 'descripcion', 'inventariominimo',
                    'preciodecosto', 'preciodeventa', 'activoactualmente', 'categoria')


class PersonaAdmin(admin.ModelAdmin):
    list_display = ('nombre', 'apellido', 'telefono', 'email',
                    'activoactualmente', 'tipodepersona')

class OrdenAdmin(admin.ModelAdmin):
    list_display = ('nrodeorden', 'nrodetransaccion', 'persona', 'mediodepago',
                    'tipodeenvio', 'importeneto', 'importeiva', 'importetotal')
     
class TipoDeEnvioAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')
            
class EstadoDeOrdenAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')
    
class MedioDePagoAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')   

class TipoDeOperacionAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')     
    
class TipoDePersonaAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')         
   
class OrdenDetalleAdmin(admin.ModelAdmin):
    list_display = ('orden', 'producto', 'cantidad')            
   
class OperacionAdmin(admin.ModelAdmin):
    list_display = ('id', 'persona', 'tipodeoperacion', 'producto', 'cantidad')      
    
class ProductoDestadoAdmin(admin.ModelAdmin):
    list_display = ('id', 'producto', 'fechadesde', 'fechahasta')     
    
class BodegaAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')     
    
class PuntoClaveAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')         
    
class TipoDeVinoAdmin(admin.ModelAdmin):
    list_display = ('id', 'nombre')     

class PuntoClavePorProductoAdmin(admin.ModelAdmin):
    list_display = ('id', 'producto', 'puntoclave')         
         
                       
admin.site.register(Categoria, CategoriaAdmin)
admin.site.register(Producto, ProductoAdmin)
admin.site.register(TipoDeEnvio, TipoDeEnvioAdmin)
admin.site.register(EstadoDeOrden, EstadoDeOrdenAdmin)
admin.site.register(MedioDePago, MedioDePagoAdmin)
admin.site.register(Persona, PersonaAdmin)
admin.site.register(Orden, OrdenAdmin)
admin.site.register(OrdenDetalle, OrdenDetalleAdmin)
admin.site.register(Operacion, OperacionAdmin)
admin.site.register(TipoDeOperacion, TipoDeOperacionAdmin)
admin.site.register(TipoDePersona, TipoDePersonaAdmin)
admin.site.register(ProductoDestacado, ProductoDestadoAdmin)
admin.site.register(Bodega, BodegaAdmin)
admin.site.register(TipoDeVino, TipoDeVinoAdmin)
admin.site.register(PuntoClave, PuntoClaveAdmin)
admin.site.register(PuntoClavePorProducto, PuntoClavePorProductoAdmin)