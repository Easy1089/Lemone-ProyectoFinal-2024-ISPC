# Generated by Django 5.0.4 on 2024-05-07 10:42

import django.utils.timezone
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('apilemone', '0010_rename_productodestado_productodestacado'),
    ]

    operations = [
        migrations.AlterField(
            model_name='productodestacado',
            name='fechamodificacion',
            field=models.DateField(blank=True, default=django.utils.timezone.now, null=True),
        ),
    ]
