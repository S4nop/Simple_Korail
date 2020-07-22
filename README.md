# Simple_Korail_Application
---
## Introduce
This application offers features including  
1. Search train list
2. Reserve train
3. Automatically notice when the train you want is available
---
## Structure
### Activity
* [LoginActivity](app/src/main/java/com/example/korailauto/LoginActivity.java)  
  Activity to login to the Korail homepage.  
  Account is automatically saved to `SharedPreference`  
  and automatically fill id/pw textbox from the next run.  
  Use `LoginRequest` to log in.  
* [MainActivity](app/src/main/java/com/example/korailauto/MainActivity.java)  
  Activity to do most of the works of this application.  
  It has two layouts, `ConstraintLayout` for searching feature and  
  `ScrollView` for showing train list.

### Requests
* [Request](app/src/main/java/com/example/korailauto/Request.java)  
  Http request class using `Jsoup`.
  ```JAVA
  Reqeust request = new Request();
  request.request('URL to connect', 'Origin', 'Referer', cookies);
  Document result = request.getReqResult();
  ``` 

* <strong>TrainListRequest</strong>  
  Inherited class of `Request`  
  Request train list from Korail web page and return html `#tableResult`   

* <strong>ReserveRequest</strong>  
  Inherited class of `Request`
  Request train reservation using given train info.  

<em>※ To prevent abuse, these two codes are not included in this 
repository</em>

* [LoginReqeust](app/src/main/java/com/example/korailauto/LoginRequest.java)  
  It isn't inherited class of `Request` cause it needs different way of excuting  
  Request login to Korail web page using user id and pw.  

### Service
* [CheckerService](app/src/main/java/com/example/korailauto/CheckerService.java)  
  Service to check train's available status and notice the result using `Notificator`.  

### About data structure 'train'
* [ITrain](app/src/main/java/com/example/korailauto/ITrain.java)  
  Base Interface of train structure
* [Train](app/src/main/java/com/example/korailauto/Train.java)
  Train data structure  
  Contains train data, table row as itself, reservation function.
* [TrainMaker](app/src/main/java/com/example/korailauto/TrainMaker.java)  
  Contains function parsing and making `Train` using `TrainListRequest`'s result.


---
## TODO
1. Need Background image of LoginActivity.
2. Implement Activity to manage Services.
3. Implement reservation result checking function.
  