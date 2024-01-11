package com.android.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.android.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 콜백 인스턴스 생성
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로가기(back) 버튼 클릭 시 이벤트
            // 다이얼로그 생성
            val builder = AlertDialog.Builder(this@MainActivity)
            // 제목
            builder.setTitle("종료")
            // 콘텐츠 영역
            builder.setMessage("정말 종료하시겠습니까?")
            // 제목 옆의 아이콘
            builder.setIcon(R.drawable.chat)

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE ->
                            finish()

                        DialogInterface.BUTTON_NEGATIVE ->
                            dialog?.dismiss()
                    }
                }
            }
            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)

            builder.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // 위에서 생성한 인스턴스를 붙여준다
        this.onBackPressedDispatcher.addCallback(this, callback)

        // 데이터 원본 준비
        val dataList = mutableListOf<MyItem>()
        dataList.add(
            MyItem(
                R.drawable.sample1,
                "산지 한달된 선풍기 팝니다",
                "이사가서 필요가 없어졌어요 급하게 내놓습니다",
                "대현동",
                "서울 서대문구 창천동",
                1000,
                25,
                13,
                false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample2, "김치냉장고", "이사로인해 내놔요", "안마담", "인천 계양구 귤현동", 20000, 28, 8,false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample3,
                "샤넬 카드지갑",
                "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",
                "코코유",
                "수성구 범어동",
                10000,
                5,
                14,
                false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample4,
                "금고",
                "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다",
                "Nicole",
                "해운대구 우제2동",
                10000,
                17,
                14,
                false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample5,
                "갤럭시Z플립3 팝니다",
                "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!\n",
                "절명",
                "수원시",
                150000,
                9,
                22,
                false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample6,
                "프라다 복조리백",
                "까임 오염없고 상태 깨끗합니다\n정품여부모름",
                "미니멀하게",
                "수원시 영통구 원천동",
                50000,
                16,
                25,false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample7,
                "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",
                "굿리치",
                "남구 옥동",
                150000,
                54,
                142,
                false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample8,
                "샤넬 탑핸들 가방",
                "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n  \n색상 : 블랙\n사이즈 : 25.5cm * 17.5cm * 8cm\n구성 : 본품더스트\n\n급하게 돈이 필요해서 팝니다 ㅠ ㅠ",
                "난쉽",
                "동래구 온천제2동",
                180000,
                7,
                31,
                false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample9,
                "4행정 엔진분무기 판매합니다.",
                "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n",
                "알뜰한",
                "원주시 명륜2동",
                30000,
                28,
                7,
                false
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample10,
                "셀린느 버킷 가방",
                "22년 신세계 대전 구매입니당\n \"+ \"셀린느 버킷백\n\" + \"구매해서 몇번사용했어요\n\" + \"까짐 스크래치 없습니다.\n\" + \"타지역에서 보내는거라 택배로 진행합니당!\"",
                "똑태현",
                "중구 동화동",
                190000,
                6,
                40,
                false
            )
        )

        // 어댑터 생성 후 데이터리스트 넣어준다
        val adapter = MyAdapter(dataList)
        // 만든 어댑터를 recyclerView 어댑터에 넣어준다
        binding.recyclerView.adapter = adapter
        // layoutManager = 레이아웃을 어떻게 구성할건지
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 리사이클러 뷰 아이템들 사이에 구분선 추가
        val decoration = DividerItemDecoration(applicationContext, VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)

        // MyAdapter에서 받은 클릭 이벤트 처리
        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                // 선택된 아이템
                val selectedItem = dataList[position]
                // Intent로 데이터를 DetailActivity로 보내준다
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(Const.ITEM_OBJECT, selectedItem)
                startActivity(intent)
            }
        }

        // 롱클릭 시 다이얼로그 및 아이템 삭제
        adapter.itemLongClick = object :MyAdapter.ItemLongClick{
            override fun onLongClick(view: View, position: Int) {
                val removeDialog = AlertDialog.Builder(this@MainActivity)
                removeDialog.setIcon(R.drawable.chat)
                removeDialog.setTitle("상품 삭제")
                removeDialog.setMessage("상품을 정말 삭제하시겠습니까?")
                removeDialog.setPositiveButton("확인"){ dialog, _ ->
                    dataList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }
                removeDialog.setNegativeButton("취소"){ dialog, _ ->
                    dialog.dismiss()
                }
                removeDialog.show()
            }
        }

        // Spinner 리스트
        val village = arrayOf("내배캠동", "스파르타동", "코딩클럽동")
        // Spinner 객체 생성
        val villageSpinner = binding.spTopbar
        // Spinner 어댑터 생성
        val spAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, village)
        villageSpinner.adapter = spAdapter


        // 알림 버튼(상단 종모양) 클릭 시 notification() 호출
        binding.ibNotification.setOnClickListener {
            notification()
        }

        // 플로팅 버튼(페이드효과) - 스크롤 상단 이동
        // AlphaAnimation()를 이용해 괄호 안의 숫자는 투명도를 의미한다
        // 괄호 안의 숫자는 float 형이고 범위는 0.0 ~ 1.0
        // setDuration을 이용해 지속시간을 설정한다 (지정해준 투명도를 몇초동안 실행할 것인지. 100 = 1초)
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        // isTop 변수를 선언해서 true일 때 최상단, false일 때 최상단이 아님을 구분지어준다
        var isTop = true

        // 플로팅 버튼(스크롤리스너 재정의) - 스크롤 상단 이동
        // recyclerView의 Scroll 상태변화에 따라 변화를 주어야 하므로 이름 감지하기 위해서
        // RecyclerView의 addOnScrollListener를 이용하여 onScrollStateChanged(스크롤 상태가 변경될 때마다 호출)를 재정의하고
        // 스크롤을 움직일 때마다 canScrollVertically 메서드를 이용하여 확인하면 된다
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // canScrollVertically = 특정 방향으로 수직 스크롤을 할 수 있는지 확인하며 음수(-1)일 경우 최상단, 양수(1)일 경우 최하단을 확인한다
                // 결과 값으로 스크롤 될 수 있으면 true, 그렇지 않으면 false를 반환한다
                // SCROLL_STATE_IDLE = 현재 스크롤 되지 않는 상태
                // 스크롤에 인한 중복 발생을 방지하기 위해서 조건에 추가해준다
                if (!binding.recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.fbFloating.startAnimation(fadeOut)
                    // View.INVISIBLEG = 보이지 않고 어떤 이벤트, 동작도 하지 않지만 자리는 차지
                    // View.GONE = 보이자 않고 어떤 이벤트, 동작도 하지 않으며 자리조차 차지 x
                    binding.fbFloating.visibility = View.GONE
                    isTop = true
                } else {
                    if (isTop) {
                        binding.fbFloating.startAnimation(fadeIn)
                        binding.fbFloating.visibility = View.VISIBLE
                        isTop = false
                    }
                }
            }
        })
        // 플로팅 버튼(클릭 이벤트 처리) - 스크롤 상단 이동
        binding.fbFloating.setOnClickListener {
            // smoothScrollToPosition(0) = 리스트의 0번째 항목으로 부드럽게 이동한다
            binding.recyclerView.smoothScrollToPosition(0)
        }

    }

    // 알림 기능
    private fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        // 안드로이드 버전이 8.0 (26)이상인지 체크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 채널 ID
            val channelID = "one-channel"
            // 채널 이름
            val channelName = "My channel One"
            // 채널 생성(ID, 이름, 중요도)
            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                // 채널에 다양한 정보 설정
                // 채널 설명
                description = "My Channel One Description"
                // 배지
                setShowBadge(true)
                // 알람이 울리는 링톤 (알림 오디오)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
                setSound(uri, audioAttributes)
                // 알림 진동
                enableVibration(true)
            }
            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)
            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelID)
        } else {
            // 안드로이드 버전이 26 이하일 때
            builder = NotificationCompat.Builder(this)
        }

        // 알림의 기본 정보
        builder.run {
            // 알림 아이콘
            setSmallIcon(R.drawable.alarm)
            // 알림 발생시간
            setWhen(System.currentTimeMillis())
            // 알림 제목
            setContentTitle("키워드 알림")
            // 알림 세부 텍스트
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }
        manager.notify(11, builder.build())
    }
}
