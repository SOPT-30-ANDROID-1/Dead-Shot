# Dead-Shot

> 잊지마, 우린 나쁜놈들이야.

<img src="https://user-images.githubusercontent.com/33388801/161442839-c1bdc8da-0c91-4ee5-9c45-22b953b53136.png" width=250/>




# 3차 세미나

## 프로그램 실행 영상

https://user-images.githubusercontent.com/48896148/167121996-c61a9cd8-85cc-47b9-93d1-8bc5f0121e02.mp4

## 필수과제

### 1. font-family를 활용한 noto_sans_kr.xml
``` Kotlin
<?xml version="1.0" encoding="utf-8"?>
<font-family xmlns:android="http://schemas.android.com/apk/res/android">

    <font
        android:font="@font/noto_sans_kr_small"
        android:fontWeight="100"/>

    <font
        android:font="@font/noto_sans_kr_light"
        android:fontWeight="200"/>

    <font
        android:font="@font/noto_sans_kr_regular"
        android:fontWeight="300"/>

    <font
        android:font="@font/noto_sans_kr_medium"
        android:fontWeight="400"/>

    <font
        android:font="@font/noto_sans_kr_bold"
        android:fontWeight="500"/>

    <font
        android:font="@font/noto_sans_kr_black"
        android:fontWeight="600"/>

</font-family>
```
굵기가 얇은 순서대로 fontWeight의 값을 100씩 키웠습니다.

### 2. 기존 HomeActivity --> ProfileFragment 에 구현하기

1) 어플 구획을 위하여 ConstraintLayout을 fragment안에 추가로 구성
``` kotlin
<androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/cl_gray"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#444444"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="100dp"
            android:src="@drawable/ic_option"
            app:layout_constraintStart_toEndOf="@id/iv_profile"
            app:layout_constraintTop_toTopOf="@id/iv_profile" />


        <ImageView
            android:id="@+id/iv_profile"
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_marginTop="44dp"
            android:src="@drawable/image_github"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <TextView
            android:id="@+id/tv_profile"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:text="ChoiWooHyeong"
            android:fontFamily="@font/noto_sans_kr_bold"
            android:textAppearance="@style/text_style_user_name"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/iv_profile" />

        <TextView
            android:id="@+id/tv_insta_id"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="11dp"
            android:text="\@vvoo__hyeong"
            android:fontFamily="@font/noto_sans_kr_regular"
            android:textColor="#DFDAED"
            android:textSize="14sp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/tv_profile" />

        <TextView
            android:id="@+id/tv_about_user"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="11dp"
            android:layout_marginBottom="15dp"
            android:text="개발자 꿈나무"
            android:textColor="#8F8F8F"
            android:fontFamily="@font/noto_sans_kr_medium"
            android:textSize="14sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/tv_insta_id" />
    </androidx.constraintlayout.widget.ConstraintLayout>
```
프레그먼트 내의 ImageView ~ 유저 소개용 TextView (개발자 꿈나무) 까지 새로운 레이아웃 내에 구현 후 배경 색상 바꿔주었음.

2) 버튼에 셀렉터 활용
``` kotlin
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/round_angle_button_selected" android:state_selected="true" />
    <item android:drawable="@drawable/round_angle_button_non_selected" android:state_selected="false"/>
</selector>
```
위와 같은 background 요소를 만들어 주었음. 이전 시간에 만든 @drawable/round_angle_button에서 색상을 수정하여 selected, non_selected일때의 요소들을 만들어 버튼이 선택될 때와 아닐 때의 background를 구분해 주었습니다.

3) 원형 이미지 활용하였으며, BottomNavigation 의 경우 Figma에서 svg를 export하여 구현하였습니다.
(프로그램 실행 영상 참고)

### 3. HomeFragment

2. 로그인 버튼, 회원 가입 버튼 클릭시 작동하는 SignInActivity의 코드
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
ActivityMainBinding으로부터 binding 객체를 생성합니다.
logInButton에 리스너를 달아줍니다.
id, password 변수를 만들고 채워져 있는 텍스트를 확인하는 코드를 if함수를 이용하여 작성하였음.

signButton에 리스너를 달아줍니다.
Intent 를 통해 signUpActivity를 불러옵니다.


#### 비밀번호를 적는 EditTextView에서 입력 내용 가리기
``` Kotlin
android:inputType="textPassword"
```
EditText 레이아웃 작성시 위 코드를 추가하여 입력 내용을 가려줍니다.

#### EditTextView에 미리보기 글씨 넣기
``` Kotlin
android:hint="비밀번호를 입력해 주세요."
```
EditText 레이아웃 작성시 위 코드를 추가하여 미리보기 글씨를 넣어 줍니다.

### SignUpActivity, activity_sign_up

1) TabLayout과 ViewPager2

```kotlin
<com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="23dp"
        app:layout_constraintTop_toBottomOf="@id/tv_github"
        app:tabIndicatorColor="@color/sopt_main_purple"/>

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_home"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tab_layout"
        app:layout_constraintVertical_bias="0.0"
        tools:layout_editor_absoluteX="0dp" />
```

tabIndicatorColor에 색상을 지정해주었습니다.

색상은 sopt_main_purle로 미리 지정해놓은 색상을 활용했습니다.
3차 세미나때 배운 내용을 활용하였으며, 프로그램 실행 영상에서 정상적으로 작동하는것을 볼 수 있습니다.
