# Kardex Inventory

Esta aplicacion gestiona el stock, entradas y salidas de mercaderia

## Empecemos

Simplemente ejecutar el siguiente comando
```
mvn clean spring-boot:run
```

### Pre requisitos

Este proyecto utiliza lombok

1) Si ud es usuario de eclipse deberia bajarlo desde aqui [here](https://projectlombok.org/download) y seguir las instrucciones.

2) Si ud utiliza intellij vaya a preference -> plugins y luego instale el plugin de lombok.



# Endpoints

## Venta de productos / retorna los producto que fueron vendido exitosamente.

### Descripcion


Controller: CartController

Method: PUT

Este servicio se utiliza para decrementar en el stock aquellos productos vendidos, retornando los mismos

### Params
```
curl --location --request PUT 'localhost:8182/kardex-api/cart/out' \
--header 'Content-Type: application/json' \
--data-raw '{
    "products":[
        {
            "productId":1,
            "productName":"Vasos Marvel",
            "stock":50,
            "amount":51
        },
        {
            "productId":2,
            "productName":"Velas Marvel",
            "stock":30,
            "amount":12
        }
    ]
}'
```

## Incremento del stock en el inventario / retorna la existencia real en la db de productos luego del agregado a partir de retornar el producto.

### Description

Controller: InventoryController

Method: PUT

Este servicio toma un producto existente en la db e incrementa su propiedad stock actualizandolo.

### Params
```
curl --location --request PUT 'localhost:8182/kardex-api/inventory/in' \
--header 'Content-Type: application/json' \
--data-raw '{
    "categoryId":1,
    "categoryName":"Marvel",
    "products":[
        {
            "productId": 1,
            "productName": "Vasos Marvel",
            "stock": 50,
            "amount": 100
        },
        {
            "productId": 2,
            "productName": "Velas Marvel",
            "stock": 50,
            "amount": 100
        }
    ]
}'
```

## Incremento del stock en el inventario / retorna la existencia real en la db de productos luego del agregado a partir de retornar el producto.

### Description

Este servicio obtiene todas las categorias y a su vez todos los productos pertenecientes a la misma los envuelve en un objeto del tipo InventoryDTO
y los devuelve

### Params
```
curl --location --request GET 'localhost:8182/kardex-api/inventory/all'
```



## Consulta / Lista todo el inventario / retorna todo el inventario disponible en la db.

### Description

### Params
```
```

## Consulta / Lista el inventario correspondiente a una categoria.

### Description

Este servicio consulta en la base de datos por un producto correspondiente a una categoria los envuelve en un objeto del tipo InventoryDTO y los
devuelve.

### Params
```
curl --location --request GET 'localhost:8182/kardex-api/inventory/category/1'
```


## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - Framework
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Guillermo Gorno**