# Generated by Django 4.2.2 on 2023-06-12 21:05

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('authentication', '0003_alter_customuser_date_joined'),
    ]

    operations = [
        migrations.AddField(
            model_name='customuser',
            name='direccion',
            field=models.CharField(default='', max_length=250),
        ),
        migrations.AddField(
            model_name='customuser',
            name='imagen',
            field=models.CharField(default='', max_length=250),
        ),
    ]
