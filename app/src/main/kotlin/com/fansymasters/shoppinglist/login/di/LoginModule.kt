package com.fansymasters.shoppinglist.login.di

import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.login.usecase.LoginUserActions
import com.fansymasters.shoppinglist.login.usecase.LoginUserUseCase
import com.fansymasters.shoppinglist.login.usecase.RegisterUserGoogleActions
import com.fansymasters.shoppinglist.login.usecase.RegisterUserGoogleUseCase
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
}