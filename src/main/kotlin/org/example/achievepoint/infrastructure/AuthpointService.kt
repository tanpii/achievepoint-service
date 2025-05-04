package org.example.achievepoint.infrastructure

import kotlinx.coroutines.runBlocking
import net.devh.boot.grpc.client.inject.GrpcClient

import org.springframework.stereotype.Service
import ru.tanpii.achievepoint.user.UserDataServiceGrpcKt
import ru.tanpii.achievepoint.user.getUserDataIdRequest
import ru.tanpii.achievepoint.user.getUserDataJwtRequest
import java.util.*

@Service
class AuthpointService {

    @GrpcClient("authpoint")
    private lateinit var gandlafClient: UserDataServiceGrpcKt.UserDataServiceCoroutineStub

    fun getUserByToken(token: String) = runBlocking {
        gandlafClient.getUserDataByJwt(getUserDataJwtRequest { jwt = token })
    }

    fun getUserById(uuid: UUID) = runBlocking {
        gandlafClient.getUserDataById(getUserDataIdRequest { this.uuid = uuid.toString() })
    }
}
