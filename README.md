# CleanMap project

### Info
This is backend part of CleanMap project (for university), which will store data regarding users and marks on map.

## API Refs:

#### New client registration

````
    POST - /api/v1/client
````

Request body (JSON):
````
    {"firstName":"...", "lastName":"...", "email":"...", "password":"..."}
````

#### Login

````
    POST - /api/v1/client/login
````

Request body (JSON):
````
    {"email":"...", "password":"..."}
````

As the result you will receive authentication token, which you should use in protected routes


#### Logout

````
    DELETE - /api/v1/client/logout (AUTH_TOKEN header should be provided)
````



#### Get all marks for map stored in DB

Request:
````
    GET - /api/v1/mark
````

As the result you will receive list of mark objects, which will contain X-axis, Y-axis coordinates and type of the mark itself. Also description.


#### Create new Mark

Request:
````
    POST - /api/v1/mark
````

Request body (JSON):
````
    {"x":0.0, "y":0.0, "type": <mark_type>, "description":"..."}
````

Mark types:
* BIN
* CLOTHES_RECYCLE
* DANGEROUS_TRASH
