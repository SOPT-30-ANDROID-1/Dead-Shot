# Dead-Shot

> 잊지마, 우린 나쁜놈들이야.

<img src="https://user-images.githubusercontent.com/33388801/161442839-c1bdc8da-0c91-4ee5-9c45-22b953b53136.png" width=250/>

# 2차 세미나

## 프로그램 실행 화면

![recyclerview_record](https://user-images.githubusercontent.com/48896148/164504236-22c7ad73-1eca-429f-8184-d89524fe2b57.gif)

## 주요 코드 설명

### FollowerPageFragment.kt
``` kotlin
class FollowerPageFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerPageBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){
        val myDecoration = MyDecoration(10f, 20f, Color.BLUE)

        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        followerAdapter.userList.addAll(
            listOf(
                UserData("이강민", "1가나다라마바사아자차카타파하2가나다라마바사아자차카타파하3가나다라마바사아자차카타파하4가나다라마바사아자차카타파하5가나다라마바사아자차카타파하6"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }
}
````
* 프레그먼트에 위치한 리사이클러뷰에 어댑터를 init 시켜주는 함수 initAdapter()에 주목
* 어댑터를 초기화 하고, FollwerPageFragment의 레이아웃 xml파일을 binding을 통해 참조함.
* 어댑터에 데이터 클래스에 맞추어, 넣고자 하는 데이터를 add해줌

### fragment_follower_page.xml
``` kotlin
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context=".FollowerPageFragment">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv_follower"
        android:layout_width="match_parent"
        android:layout_height="match_parent"

        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        tools:itemCount="4"
        tools:listitem="@layout/user_list" />
</FrameLayout>
```
* 레이아웃 파일에 RecyclerView 가 생성되어있다.
* LinearLayoutManager을 통해 linearlayout으로 설정하였다.
* fragment_repository_page.xml 에는 girdlayout으로 설정되어있다.

### Adaper (FollowerAdapter)
``` kotlin
class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    val userList = mutableListOf<UserData>()
    //질문하기 :: private

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            UserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class FollowerViewHolder(
        private val binding: UserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
            binding.textName.text = data.name
            binding.textIntroduce.text = data.introduction
        }
    }
}
```
* 코틀린 공식 문서에서 권장하는 3가지 메소드들이 구현되어있다.
* 총 데이터의 개수를 알기 위한 userList 변수가 선언되어있다.
* Adapter class 내에 FollowerViewHolder class가 작성되어있고, Adapter class의 메소드에서 바로 사용된다.
* 뷰홀더는 미리 만들어논 레이아웃과 데이터를 띄워주는 역할을 한다.
* 어댑터에서는 뷰홀더에 데이터를 바인딩 시킨다!

###round_angel.xml
```kotlin
<?xml version="1.0" encoding="utf-8"?>

<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item>

        <shape android:shape="rectangle">
            <stroke android:width="2dp"
                android:color="@color/black"/>
            <solid android:color="#ffffff"/>
            <corners
                android:bottomLeftRadius="12dp"
                android:topRightRadius="12dp"
                android:bottomRightRadius="12dp"
                android:topLeftRadius="12dp"/>
        </shape>

    </item>
</layer-list>
```
* 아이템에 대한 레이아웃의 디자인을 변경하기위해 만든 파일
``` kotlin
android:background="@drawable/round_angle">
```
* background 함수를 통해 배경의 디자인을 변경
* itemDecoration을 활용할때보다 유연성이 떨어짐

