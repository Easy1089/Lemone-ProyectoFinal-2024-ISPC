# Generated by Django 4.2.2 on 2023-06-12 22:47

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('authentication', '0007_alter_customuser_direccion_alter_customuser_imagen'),
    ]

    operations = [
        migrations.AlterField(
            model_name='customuser',
            name='fechadenacimiento',
            field=models.DateField(blank=True, null=True),
        ),
    ]