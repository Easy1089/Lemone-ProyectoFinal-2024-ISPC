# Generated by Django 4.2.2 on 2023-06-12 21:59

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('authentication', '0006_alter_customuser_is_staff_alter_customuser_username'),
    ]

    operations = [
        migrations.AlterField(
            model_name='customuser',
            name='direccion',
            field=models.CharField(blank=True, default='', max_length=250),
        ),
        migrations.AlterField(
            model_name='customuser',
            name='imagen',
            field=models.CharField(blank=True, default='', max_length=250),
        ),
    ]