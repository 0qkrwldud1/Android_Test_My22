package com.example.ch18_network_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ch18_network_test.databinding.FragmentVolleyBinding
import com.example.ch18_network_test.model.ItemModel
import com.example.ch18_network_test.recycler.MyAdapter
import org.json.JSONObject


class VolleyFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVolleyBinding.inflate(inflater, container, false)
        Log.d("kk","ㅇㅇㅇㅇ")
        // &returnType=json&numOfRows=5&pageNo=1&stationName=종로구&dataTerm=DAILY&ver=1.0
        //add...................
        val url = MyApplication.BASE_URL+"/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey="+MyApplication.API_KEY+
                "&returnType="+MyApplication.returnType+"&numOfRows=5&pageNo=1&stationName="+MyApplication.stationName+
                "&dataTerm="+MyApplication.dataTerm+"&ver=1.0"

        val queue = Volley.newRequestQueue(activity)

        val jsonRequest = object: JsonObjectRequest(
            Request.Method.GET,
            url,
            null,

            Response.Listener<JSONObject> { response ->

                val jsonArray = response.getJSONArray("items")
                val mutableList = mutableListOf<ItemModel>()
                for(i in 0 until jsonArray.length()){
                    ItemModel().run {
//                        binding.itemTitle.text = model.coGrade
//                        binding.itemDesc1.text = model.coValue
//                        binding.itemDesc2.text = model.khaiValue
//                        binding.itemDesc3.text = model.so2Value
//                        binding.itemTime.text = model.dataTime

                        val article = jsonArray.getJSONObject(i)
                        so2Value = article.getString("so2Value")
                        coValue = article.getString("coValue")
                        khaiValue = article.getString("khaiValue")
                        so2Value = article.getString("so2Value")
                        dataTime = article.getString("dataTime")
                        mutableList.add(this)
                    }
                }

                binding.volleyRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.volleyRecyclerView.adapter = MyAdapter(activity as Context, mutableList)
            },
            Response.ErrorListener {
                Log.d("kkang", "error1111... $it")
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val map = mutableMapOf<String, String>(
                    "User-agent" to MyApplication.USER_AGENT
                )
                return map
            }
        }

        queue.add(jsonRequest)

        return binding.root
    }

}