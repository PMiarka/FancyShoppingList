package com.fansymasters.shoppinglist.account.login.di

import com.fansymasters.shoppinglist.account.data.LoginRepositoryImpl
import com.fansymasters.shoppinglist.account.data.LoginResponseMapper
import com.fansymasters.shoppinglist.account.data.RegisterRepositoryImpl
import com.fansymasters.shoppinglist.account.domain.LoginDto
import com.fansymasters.shoppinglist.account.domain.LoginRepository
import com.fansymasters.shoppinglist.account.domain.RegisterRepository
import com.fansymasters.shoppinglist.account.login.usecase.LoginUserActions
import com.fansymasters.shoppinglist.account.login.usecase.LoginUserUseCase
import com.fansymasters.shoppinglist.account.login.usecase.RegisterUserGoogleActions
import com.fansymasters.shoppinglist.account.login.usecase.RegisterUserGoogleUseCase
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface LoginModule {

    @Binds
    fun bindsRegisterUserGoogleUseCase(impl: RegisterUserGoogleUseCase): RegisterUserGoogleActions

    @Binds
    fun bindsLoginUserUseCase(impl: LoginUserUseCase): LoginUserActions

    @Binds
    fun bindsLoginProcessingStateReader(impl: LoginUserUseCase): ProcessingStateReader<LoginResponseDto>

    @Binds
    fun bindsLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindsRegisterRepository(impl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    fun bindsLoginResponseMapper(impl: LoginResponseMapper): Mapper<LoginResponseDto, LoginDto>
}