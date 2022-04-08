# Dead-Shot

> 잊지마, 우린 나쁜놈들이야.

<img src="https://user-images.githubusercontent.com/33388801/161442839-c1bdc8da-0c91-4ee5-9c45-22b953b53136.png" width=250/>




# 1차 세미나

2022.04.08 - 필수 과제 완료 후 readme 작성!

## 주요 코드 설명

### SignInActivity, activity_main (초기 화면이므로 xml 이름을 수정하지 않았음)

#### 로그인 버튼 -> HomeActivity로 이동
1.로그인 버튼 레이아웃
``` Kotlin
    <Button
        android:id="@+id/signUpButton"
        android:layout_width="346dp"
        android:layout_height="58dp"
        android:layout_marginTop="30dp"


        android:backgroundTint="@color/MYGREEN"

        android:text="회원가입"

        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/logInButton"
        app:layout_constraintVertical_bias="0.0" />
```
버튼의 수평, 수직 크기는 비슷한 용도의 버튼들과 통일 되어 있습니다.
네 방향의 제약 조건들도 위 코드와 같습니다.
회원가입 버튼에는 '회원가입'이라는 텍스트가 들어가 있으며, 색상은 제가 추출한 초록색으로 변경 하였습니다. @color/MYGREEN을 values 하위 폴더에 myColor.xml 내에 정의 하여 활용 하였습니다. "@color/MYGREEN" 대신에 "#XXXXXX"(XX는 RGB값의 16진수 표현)를 활용할 수 있습니다.

``` Kotlin
<resources>

    <color name = "MYGREEN">#78C257</color>

</resources>
```
활용할 색상들을 직접 정의 한 후 활용할 수 있습니다.
버튼들의 style, theme 등을 미리 지정해서 많이 사용될 버튼들을 편하게 만들 수 있습니다.

2. 로그인 버튼 클릭시 작동하는 SignInActivity의 코드
``` Kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInButton.setOnClickListener{

            val id = binding.editTextId.text.toString()
            val password = binding.editTextPassword.text.toString()

            //항목을 다 채우지 않았을 경우 토스트 메시지 띄우기
            if(id.isEmpty() || password.isEmpty() ){
                Toast.makeText(this, "아이디/비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show()
            }
            //다 채워진 경우 HomeActivity 로 이동
            else{
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
        binding.signUpButton.setOnClickListener {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        }
    }
}
```

#### 비밀번호를 적는 EditTextView에서 입력 내용 가리기
``` Kotlin

```
#### EditTextView에 미리보기 글씨 넣기
``` Kotlin

```
#### 회원가입 버튼 -> SignUpActivity로 이동
``` Kotlin

```

### SignUpActivity, activity_sign_up

#### 회원가입 버튼 -> SignInActivity로 이동
``` Kotlin

```
### HomeActivity, activity_home

#### HomeActivity
``` Kotlin

```
