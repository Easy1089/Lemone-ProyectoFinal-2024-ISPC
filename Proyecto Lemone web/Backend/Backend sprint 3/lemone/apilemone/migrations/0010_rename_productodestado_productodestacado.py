# Generated by Django 5.0.4 on 2024-05-07 10:34

from django.conf import settings
from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('apilemone', '0009_productodestado'),
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.RenameModel(
            old_name='ProductoDestado',
            new_name='ProductoDestacado',
        ),
    ]