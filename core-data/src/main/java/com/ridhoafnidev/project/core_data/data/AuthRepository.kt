package com.ridhoafnidev.project.core_data.data

import com.ridhoafnidev.project.core_domain.model.Auth
import com.ridhoafnidev.project.core_domain.model.isSuccessLogin
import com.ridhoafnidev.project.core_util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository : com.ridhoafnidev.project.core_domain.repository.IAuthRepository {
    override fun login(auth: Auth): Flow<Resource<Auth>> = flow {
        emit(Resource.Loading())
        if (auth.isSuccessLogin){
            emit(Resource.Success(auth))
        }
        else{
            emit(Resource.Error<Auth>("Email/Nomor Handphone atau Password salah"))
        }
    }
}