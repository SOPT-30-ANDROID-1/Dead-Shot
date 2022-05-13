# Dead-Shot

> 잊지마, 우린 나쁜놈들이야.

<img src="https://user-images.githubusercontent.com/33388801/161442839-c1bdc8da-0c91-4ee5-9c45-22b953b53136.png" width=250/>




# 4차 세미나

## 프로그램 실행 영상

https://user-images.githubusercontent.com/48896148/168256677-b87780d7-fd2f-494e-87cb-60c70f10e830.mp4

## postman 통신 결과 및 회원가입 성공 토스트 메시지

![슬라이드2](https://user-images.githubusercontent.com/48896148/168260927-13177e56-e856-4272-8724-93e2223cfdbf.JPG)
![슬라이드1](https://user-images.githubusercontent.com/48896148/168260933-4078418a-843a-4795-b649-c0064979a6ff.JPG)

* 옳은 비밀번호를 전달했을 때와 잘못된 비밀번호를 전달했을 때 의 postman에서의 통신결과
* 회원가입시 이미 만들어놓은 아이디를 전송하여 "duplicate"오류를 전달받음.

## 필수과제

### 1. retrofit interface / 구현체 코드 / Request & Response 객체에 대한 코드

#### 1) retrofit interface 
``` kolitn
interface SoptService {
    @POST("auth/signin")
    fun signIn(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun signUp(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>

}
```
레트로핏 인터페이스, http 함수 get, post, put, delete 중 post를 사용하여 통신하였습니다.
"'''/auth/signin"로 RequestSignIn 형식의 클래스로 만든 바디를 보내고 전달받은 Json 형태의 정보를ResponseSignIn 형식의 오브젝트로 파싱하여 활용하게됩니다.

"signup"의 경우도 위와 동일한 과정으로 통신합니다.

#### 2) retrofit interface 구현체 코드
``` kolint
object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService : SoptService = retrofit.create(SoptService::class.java)
}
```
레트로핏 인터페이스를 활용하여 실질적으로 구현하여 생성해주는 객체에 대한 코드입니다.
레트로핏 객체의 경우 싱글톤으로 제작하는 것이 바람직 하다고 합니다.
인터페이스와 실제 구현체를 분리하여 만드는 이유라고 생각합니다. ?!
BASE_URL 변수에 주소를 담아 활용하고있습니다.

#### 3) Request & Response
``` kotlin
data class RequestSignIn(
    @SerializedName("email")
    val id: String,
    val password: String
)

data class RequestSignUp(
    val name: String,
    val email : String,
    val password: String
)

data class ResponseSignIn(
    val status: Int,
    val message : String,
    val data : Data
) {
    data class Data(
        val name: String,
        val email: String
    )
}

data class ResponseSignUp(
    val status: Int,
    val message : String,
    val data : Data
) {
    data class Data(
        val id : Int
    )
}
```
각각 통신 요청시 보낼 JSON 형의 정보들을 kotlin 에서의 data class로서 구현하였습니다.
통신 결과 응답시 받을 JSON 형의 정보들에 대해서도 마찬가지입니다.
RequestSignIn에서 @SerializedName()을 활용하고 있습니다. 변수명과 JSON에서의 자료형이 일치하지 않을 경우 이를 연결하기위해 사용합니다.

## 성장 과제

### 1. Github API 연동해서 리스트로 띄우기

#### 1) Retrofit interface
``` kotlin
interface GithubFollowerListService {
    @GET("users/{username}/following")
    fun getFollowerList (
        @Path("username") username:String
    ): Call<ResponseFollowerList>
}
```
Github API에 따르면 GET함수를 사용해야하며 해당 유저에 대한 정보를 가져와야 하므로 Path()를 이용하였습니다.
!! 작업 막판에 알게되었군요, following으로 잘못 만들었습니다. follower로 만들어야하지만, 구현 방식에 차이가 없으므로 추후 코드 수정하도록 해야겠습니다.

#### 2) Retrofit interface 실제 구현체
``` kotlin
object GithubServiceCreator {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val githubFollowerListService : GithubFollowerListService = retrofit.create(GithubFollowerListService::class.java)
}
```
BASE_URL 에 Github API에서 알려준 주소를 넣어놓았습니다. 위에 만든 retrofit interface 형의 변수를 생성하였습니다.

#### 3) Response 객체
``` kotlin
class ResponseFollowerList : ArrayList<ResponseFollowerListItem>()

data class ResponseFollowerListItem(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)
```
Github 에서 객체를 담고있는 배열 형태의 자료를 JSON 형태로 제공합니다. 그래서 객체들을 배열 형태로 담아줄 ArrayList 클래스를 플러그인을 통해 만들었습니다.

Github API 에서 제공하는 Data example 과 android studio 에서 제공하는 Json to kotlin plugin 을 이용하여 Response 객체를 위한 데이터 클래스를 만들었습니다.
사용하지 않는 data들은 없애거나 주석 처리를 해놓는건 어떤가 합니다. 이 역시 알아봐야겠습니다. 
유지, 보수, 확장을 위해서는 남겨두는것도 편할 수 있을것 같기도 합니다...?

#### 4) ProfileFollowerFragment에서 callback 구현하여 recyclerview에 정보를 넣어주는 함수 구현
``` kotlin
    private fun getFollowerList() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        val username: String = "dn7638"


        val call: Call<ResponseFollowerList> =
            GithubServiceCreator.githubFollowerListService.getFollowerList(username = username)

        call.enqueue(object : Callback<ResponseFollowerList> {
            override fun onResponse(
                call: Call<ResponseFollowerList>,
                response: Response<ResponseFollowerList>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseFollowerList? = response.body()

                    if (data != null) {
                        for (i in data) {

                            followerAdapter.userList.add(
                                UserData(
                                    i.login,
                                    i.htmlUrl
                                )
                            )

                        }
                        //followerAdapter.notifyDataSetChanged() 이 부분은 일단 필요 없을 듯 함..
                    }
                }

            }

            override fun onFailure(call: Call<ResponseFollowerList>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
```
recyclerview에 정보를 담아줄 adapter객체를 생성합니다.
Call 객체를 생성하여 전달받은 데이터를 어댑터를 활용하여 recyclerview에 담는데 쓰이는 data class 에 담아 add 해줍니다.
response의 body를 data변수에 담아주었습니다. Github에서 주는 정보는 배열안에 객체들을 담아주고있기에 for 문과 배열을 활용하여 담아주었습니다.
