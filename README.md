** Installation
Step1:- Clone the repository to your local machine:
- git clone https://github.com/Kulbirr/Wishlist-Management.git
Step2:- Navigate to the project directory:
- cd WishlistManagementApplication

Step3:- Build the project using Maven:
- mvn clean install

# Configuration
Step1:- Database Configuration:
- Open the src/main/resources/application.properties file.
- Configure your database connection settings:
  - spring.datasource.url=jdbc:mysql://localhost:3306/wishlistdb
  - spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  - spring.datasource.username=root
  - spring.datasource.password=root

### Running the Application
1. Run the application using Maven:
- mvn spring-boot: run

2. The application will start on the default port 8080, if it shows an error try running in 9090.

### API Usage
1. Sign Up:
- Open Postman or any HTTP client application.
- Send a POST request to http://localhost:8080/auth/register with JSON body containing user details:
  - {
    - "userName": "example_Shivam",
    - "phoneNo": "example_8524556951",
    - "password": "example_123"
  - }

2. Login:
- Send a POST request to http://localhost:8080/auth/login with JSON body containing user credentials:
  - {
    - "userName": "example_Shivam",
    - "password": "example_123"
   - }

3. Authentication
- you can check in securityConfig class which endpoint is authenticated and which is accessible to all. if an endpoint is authenticated you can add credentials that you have registered and send request.
- you can use get apis in your browser for eg: http://localhost:9090/wishlist/getWishlist/Anime Figurines
- this URL will give you the wishlist of the user with the wishlist name of Anime figurines. 
