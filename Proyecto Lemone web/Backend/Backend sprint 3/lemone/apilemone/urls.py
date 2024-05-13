from django.urls import path
from .views import CantidadDeUsuariosView, CantidadProductosView, CategoriaView, ComprasView, OperacionView, OrdenView, ProductoDestacadoView, ProductoView, PuntosClavesPorProductoView, SumaVentasView, UsuariosView

urlpatterns = [
    path('productos/', ProductoView.as_view(), name='productos_list'),
    path('ordenes/', OrdenView.as_view(), name='ordenes_list'),
    path('categorias/', CategoriaView.as_view(), name='categorias_list'),
    path('operaciones/', OperacionView.as_view(), name='operaciones_list'),
    path('compras/', ComprasView.as_view(), name='compras'),
    path('cantidaddeusuarios/', CantidadDeUsuariosView.as_view(), name='cantidad_usuarios'),
    path('cantidaddeproductos/', CantidadProductosView.as_view(), name='cantidad_productos'),   
    path('ventas/', SumaVentasView.as_view(), name='ventas'),
    path('usuarios/', UsuariosView.as_view(), name='usuarios_list'),
    path('productosdestacados/', ProductoDestacadoView.as_view(), name='productosdestacados_list'),  
    path('puntosclavesporproducto/', PuntosClavesPorProductoView.as_view(), name='puntosclavesporproducto_list'),  
    path('puntosclavesporproducto/<int:producto_id>/',
         PuntosClavesPorProductoView.as_view(), name='puntosclavesporproducto_list'),  
    path('productos/<int:producto_id>/',
         ProductoView.as_view(), name='producto-detail'),
     path('usuarios/<int:usuario_id>/',
         UsuariosView.as_view(), name='usuario-detail'),
]
