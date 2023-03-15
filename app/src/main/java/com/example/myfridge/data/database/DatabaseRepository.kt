package com.example.myfridge.data.database

class DatabaseRepository {

    class APICallInfoRepository(
        private val dao: DatabaseDAO.APICallInfoDAO
    ) {
        suspend fun insertAPICallInfo(callInfo:APICallInfo) = dao.insert(callInfo)

        suspend fun deleteAPICallInfo(callInfo:APICallInfo) = dao.delete(callInfo)

        suspend fun updateAPICallInfo(callInfo:APICallInfo) = dao.update(callInfo)

        fun getAPICallInfo(callInfo: String) = dao.query(callInfo)

        val getAPICallHistory = dao.queryAll()
    }
}