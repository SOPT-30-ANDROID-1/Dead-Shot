# Dead-Shot

> 잊지마, 우린 나쁜놈들이야.

<img src="https://user-images.githubusercontent.com/33388801/161442839-c1bdc8da-0c91-4ee5-9c45-22b953b53136.png" width=250/>




7 4차 세미나

## 프로그램 실행 영상

https://user-images.githubusercontent.com/48896148/173035240-cfa25957-4e8b-4b4e-9da4-057f3105c270.mp4

https://user-images.githubusercontent.com/48896148/173035260-aa6dfe03-c6d7-4bec-a43f-263210e4aee8.mp4


## [필수]SharedPreferences를 사용해서 자동 로그인/자동고르인 해제 구현하기

### SOPTSharedPreferences
``` kotlin
object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val ID = "ID"
    private const val PASS_WORD = "PASS_WORD"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    //ID, PASS_WORD 를 키 값으로 하는 id, password데이터 저장하는 함수
    fun setUserData(id: String, passWord: String) {
        preferences.edit().putString(ID, id).apply()
        preferences.edit().putString(PASS_WORD, passWord).apply()
    }

    //ID 를 가져오는 함수
    fun getUserID(): String {
        return preferences.getString(ID, "").toString()
    }

    //위와 동일
    fun getUserPassWord(): String {
        return preferences.getString(PASS_WORD, "").toString()
    }

    fun setLogout(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()

        preferences.edit()
            .remove(ID)
            .clear()
            .apply()

        preferences.edit()
            .remove(PASS_WORD)
            .clear()
            .apply()
    }
}
```
#### init()
쉐어드프리퍼런스를 초기화 하는 함수입니다. context에는 불러오는 activity의 정보가 들어갑니다.

#### getAutoLogin()
AUTO_LOGIN 키에 대한 Boolean 값을 반환하는 함수입니다. 값이 없을 경우 false를 반환합니다.

#### setAutoLogin()
AUTO_LOGIN 키에 대한 Boolean 값을 저장하는 함수입니다.

#### setUserData()
자동 로그인 기능을 만드려면 ID, PASSWORD 역시 저장해야 합니다.
ID, PASS_WORD 키에 대한 String 값을 저장하는 함수입니다.

#### getUserID()
ID 키로부터 String 값을 가져오는 함수.

#### getUserPassWord()
PASS_WORD 키로부터 String 값을 가져오는 함수.

#### setLogout()
ID, PASS_WORD, AUTO_LOGIN 키들에 대한 값들을 모두 제거하는 함수.


### SignInActivity

#### initClickEvent()
``` kotlin
    //체크박스를 통해 자동 로그인 여부를 결정
    private fun initClickEvent() {
        binding.rbAutoSignIn.setOnClickListener {
            binding.rbAutoSignIn.isSelected = !binding.rbAutoSignIn.isSelected

            SOPTSharedPreferences.setAutoLogin(binding.rbAutoSignIn.isSelected)
        }
    }
```
버튼의 선택 여부를 바꿔주는 코드입니다.
또한 isSelected는 SOPTSharedPreferences.setAutoLogin의 파라미터로서 사용됩니다.

#### isAutoLogin()
``` kotlin
private fun isAutoLogin() {
        if (SOPTSharedPreferences.getAutoLogin()) {

            val requestSignIn = RequestSignIn(
                id = SOPTSharedPreferences.getUserID(),
                password = SOPTSharedPreferences.getUserPassWord()
            )

            val call: Call<ResponseSignIn> = ServiceCreator.soptService.signIn((requestSignIn))
            call.enqueueUtil(
                onSuccess = {
                    Toast.makeText(
                        this@SignInActivity,
                        "${it.data.email}님 반갑습니다! 자동 로그인 되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                },
                onError = {
                    Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            )
            finish()
        }
    }
```
getAutoLogin을 통해 자동 로그인이 설정 되어있는지 확인합니다. 만약 설정이 되어있다면,,
서버 통신을 SOPTSharedPReferences의 값들을 통해 진행하도록 합니다.
이후 서버 통신 결과 로그인 성공시 자동 로그인 되었습니다. 라는 토스트 메시지가 떠오릅니다.
이후 HomeActivity가 시작되며 이전 액티비티는 종료됩니다.

### SettingAcvivity

#### initListener()
``` kotlin
    private fun initListener(){
        binding.btAutoLoginRelease.setOnClickListener(){
            SOPTSharedPreferences.setLogout(this)
            Toast.makeText(this, "자동완성이 취소되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
```
자동 로그인 기능을 해제하는 버튼을 구현하였습니다.
setLogout() 함수를 실행하여 키에 대한 값들을 모두 해제합니다.

## [성장] 온보딩 화면 만들기
NavigationComponent 에 대한 이미비 입니다. 코드보다 전체적인 구조를 설명하기위해 이미지 첨부합니다.
![image](https://user-images.githubusercontent.com/48896148/173040006-e84406ca-17a4-4c4e-90dc-c1984acdd5cb.png)

OnboardingActivity 에는 Fragments를 담을 FragmentContainerView 가 있습니다. 3개의 Fragment가 navigationComponent에 의해 연결되어 버튼을 누를 시 하나씩 넘어가고, 마지막 fragment에서 버튼을 누르면 SigninActivity가 시작되며 이전 액티비티는 finish()됩니다.
