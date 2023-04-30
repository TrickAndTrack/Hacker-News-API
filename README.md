# Hacker-News-API
# How to Run the Project?
1. Clone the repository.
2. Navigate to the root directory of the project.
3. Run the maven build command: *mvn clean install*
4. Start the Redis server on you machine using command: *sudo systemctl start redis-server*, Check if server Runing or Not with fallowing command *sudo redis-cli*
5. Run the Project
6. Excute fallowing Url :
a. http://localhost:8080/top-stories
b. http://localhost:8080/comments?storyId={storyId}
c. http://localhost:8080/past-stories

# fallowing Output on system->

## A. http://localhost:8080/top-stories
![image](https://user-images.githubusercontent.com/73180409/235356338-bdf0dac3-53dc-4e07-8df1-3cef0a915d9a.png)

## B. http://localhost:8080/comments?storyId={storyId}
![image](https://user-images.githubusercontent.com/73180409/235356378-408087ea-90ec-4298-9b96-d6de4d1aa44e.png)

## C. http://localhost:8080/past-stories
![image](https://user-images.githubusercontent.com/73180409/235356417-ab6a3df9-5c73-44f2-b2cb-ca90fb6d2c80.png)

