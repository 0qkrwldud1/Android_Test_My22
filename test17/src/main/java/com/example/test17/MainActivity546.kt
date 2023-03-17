package com.example.test17

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.example.test17.databinding.ActivityMain546Binding
import com.example.test17.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

class MainActivity546 : AppCompatActivity() {
    lateinit var binding: ActivityMain546Binding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain546Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )

            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.test17.fileprovider",
                file
            )

            // 공유 프 -> 파일 값 저장
            // imgLoadTest 파일의 이름으로 저장
            val pref = getSharedPreferences("imgLoadTest", Context.MODE_PRIVATE)
            // 키, 값 형태로 저장
            // commit -> 실제 저장소파일에 저장
            pref.edit().run {
                putString("imgUri", "test")
                commit()
            }

            val resultStr2: String? = pref.getString("imgUri", "default")
            val result3 = resultStr2.toString()
            Log.d("kk", "imgInfo result3 결과 : $resultStr2")
            Log.d("kk", "imgInfo result3 결과 : $result3")

            // 카메라 앱 사진 촬영
            // 앱별 저장소에 저장
            // 결과 이미지뷰애 붙이기
            // 사진의 저장소 위치의 절대 경로를 공유 프레퍼런스 파일에 저장
            // 공유 프레퍼런스에 저장된 사진파일의 절대경로를 불러오기
            // 절대경로의 uri값 가져오기
            // uri값 비트맵 변환작업
            // 이미지뷰에 붙여넣기

            val uritest = Uri.fromFile(File(filePath))
            Log.d("kkang", "uritest: " + uritest.toString())

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)
        }

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            try {
                val calRatio = calculateInSampleSize(
                    // calculateInSampleSize
                    // 사진의 크기를 비율에 맞게 재조정
                    it.data!!.data!!,
                    150,
                    150
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null

                bitmap?.let {
                    binding.userImageView.setImageBitmap(bitmap)
                } ?: let {
                    Log.d("kkang", "bitmap null")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.galleryButton.setOnClickListener {
            //gallery app........................
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        binding.button1.setOnClickListener {
            //파일 쓰기
            val file: File = File(getExternalFilesDir(null), "test3.txt")
            val writeStream: OutputStreamWriter = file.writer()
            writeStream.write("hello world")
            writeStream.flush()
            // 파일 읽기
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("kkang", "$it")
            }
        }
        binding.button2.setOnClickListener {
            //공용저장소...........
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
            )
            val cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
            )
            cursor?.let {
                while (cursor.moveToNext()) {
                    Log.d("kkang", "_id : ${cursor.getLong(0)}, name : ${cursor.getString(1)}")
                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        cursor.getLong(0)
                    )

                    val uritest = Uri.fromFile(File(filePath))
                    Log.d("kkang", "uritest: " + uritest.toString())
//                    val resolver = applicationContext.contentResolver
//                    resolver.openInputStream(contentUri).use { stream ->
//                        // stream 객체에서 작업 수행
//                        val option = BitmapFactory.Options()
//                        option.inSampleSize = 10
//                        val bitmap = BitmapFactory.decodeStream(stream, null, option)
//                        binding.resultImageView.setImageBitmap(bitmap)
//                    }
                }
            }

            // 공유 프레퍼런스에 저장된 값을 불러오기
            val pref = getSharedPreferences("imgLoadTest", Context.MODE_PRIVATE)
            val imgfileUri: String? = pref.getString("imgfileUri", "값이 없으면 디폴트값이 옵니다.")
            val imgfile: String? = pref.getString("imgfile", "값이 없으면 디폴트값이 옵니다.")
            val resolver = applicationContext.contentResolver
            if (imgfileUri != null) {
                resolver.openInputStream(imgfileUri.toUri()).use { stream ->
                    // stream 객체에서 작업 수행

                    val calRatio = calculateInSampleSize(
                        Uri.fromFile(File(filePath)),
                        resources.getDimensionPixelSize(R.dimen.imgSize),
                        resources.getDimensionPixelSize(R.dimen.imgSize)
                    )
                    val option = BitmapFactory.Options()
                    option.inSampleSize = calRatio

//                    val option = BitmapFactory.Options()
//                    option.inSampleSize = 10
                    val bitmap = BitmapFactory.decodeStream(stream, null, option)
                    binding.resultImageView.setImageBitmap(bitmap)
                }
            }
        }
    }


    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}

//  공유 프레퍼런스 실습
//        1. 회원가입 이름 나이 사진
//        카메라 , 갤러리
//
//        메인 사진, 이름, 나이
//
//        액티비티 화면 2개
//        회원가입 창
//        메인화면
//
//        회원가입 입력창에서 이메일, 패스워드 프로필이미지
//        회원가입 버튼 누르면 메인창에서 입력된 정보 불러오기
//        이용한는  저장소 공유 프레퍼런스
//
//        저장후 불러오기
//
//        새모듈.

//        프로필 카레라로 찍은 이미지로 사용
//        카메라 촬영 회원가입 창 프로필 이미지에 보이기
//        파일로 저장