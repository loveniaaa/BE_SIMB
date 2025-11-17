#  BE_SIMB — Backend Sistem Informasi Manajemen Bantuan

BE_SIMB adalah backend service berbasis **Java Spring Boot** yang digunakan untuk mendukung Sistem Informasi Manajemen Bantuan (SIMB). Backend ini menangani proses autentikasi, manajemen data, validasi, penyimpanan database, dan penyediaan API untuk frontend FE_SIMB.

Dokumentasi ini mencakup:
- Instalasi Java  
- Instalasi PostgreSQL  
- Setup database  
- Konfigurasi project  
- Penjelasan arsitektur  
- Cara menjalankan backend  
- Dokumentasi API  
- Deployment (Local, VPS, Docker)  
- Best practice keamanan  

README ini dibuat **sangat lengkap** agar dapat digunakan sebagai dokumentasi resmi proyek.

---

# 1. Persyaratan Sistem

Sebelum menjalankan BE_SIMB, pastikan anda telah menginstall:

| Software | Versi Minimum |
|---------|----------------|
| Java JDK | 17 atau lebih |
| Maven | 3.8+ |
| PostgreSQL | 13+ |
| Git | Latest |
| Postman / Swagger | Opsional |

---

# 2. Instalasi Java (JDK)

## 2.1 Download JDK 17
Unduh dari Adoptium:

https://adoptium.net/

Pilih:  
```

Temurin JDK 17 (LTS)

````

## 2.2 Verifikasi Instalasi
```bash
java -version
````

Output yang benar:

```
openjdk version "17.x.x"
```

---

# 3. Instalasi PostgreSQL

## 3.1 Download PostgreSQL

[https://www.postgresql.org/download/](https://www.postgresql.org/download/)

Install PostgreSQL + pgAdmin.

## 3.2 Informasi Default

* Username: `postgres`
* Password: (diatur sendiri)
* Port: `5432`

## 3.3 Verifikasi PostgreSQL

```bash
psql --version
```

---

# 4. Membuat Database SIMB

### 4.1 Buat database via pgAdmin

Create → Database → isi:

```
scholarship_management_system
```

### 4.2 Atau buat via terminal:

```bash
psql -U postgres
CREATE DATABASE scholarship_management_system;
```

Cek database:

```sql
\l
```

# 6. Konfigurasi `application.properties`

Buka:

```
src/main/resources/application.properties
```

Isi:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/simb_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=8080
```

---

# 7. Konfigurasi Environment Production

File:

```
src/main/resources/application.properties
```

Contoh isi:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/simb_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_STRONG_PASSWORD

spring.jpa.hibernate.ddl-auto=none
logging.level.org.hibernate.SQL=ERROR
```

Menjalankan dalam mode prod:

```bash
java -jar ScholarshipManagementSystemApplication.jar --spring.profiles.active=prod
```

---

# 8. Penjelasan Arsitektur Backend

### Layering Architecture (Clean Architecture Style)

1. **Controller Layer**

    * Menghandle request dari frontend
    * Memanggil service
    * Mengembalikan response JSON

2. **Service Layer**

    * Menyimpan seluruh business logic
    * Menjaga controller tetap clean

3. **Repository Layer**

    * Berinteraksi langsung dengan database
    * Menggunakan Spring Data JPA

4. **Entity / Model**

    * Representasi tabel PostgreSQL

5. **Validation Layer**

    * Menggunakan `@Valid` dan `@NotNull`, dll.

### Security (opsional bila pakai JWT)

* JWT untuk autentikasi
* Spring Security filter
* Password hashing dengan BCrypt

---

# 9. Cara Menjalankan Backend

## 9.1 Jalankan Menggunakan Maven

```bash
mvn spring-boot:run
```

## 9.2 Build Menjadi .JAR

```bash
mvn clean package
```

Menjalankan:

```bash
java -jar target/ScholarshipManagemenSystemApplication-0.0.1-SNAPSHOT.jar
```

Backend berjalan di:

```
http://localhost:9900/
```

---
## 10.1 Autentikasi

### **POST /api/auth/login**

Request:

```json
{
  "role": "01",
  "username": "admin",
  "password": "123456"
}
```

Response:

```json
{
  "status": "success",
  "token": "xxxxx"
}
```

---

## 10.2 Users

### **GET /api/users**

Mengambil semua user.

### **POST /api/users**

Menambah user.

### **PUT /api/users/{id}**

Mengupdate data user.

### **DELETE /api/users/{id}**

Menghapus user.

---

# 11. Testing API

Anda dapat menguji API menggunakan:

### **Postman**

Import file JSON (opsional).

### **Swagger UI**

Install dependency:

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.5.0</version>
</dependency>
```

Akses:

```
http://localhost:9900/swagger-ui/index.html
```

---
