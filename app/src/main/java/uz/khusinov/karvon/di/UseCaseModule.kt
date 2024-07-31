package uz.khusinov.karvon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.khusinov.karvon.domain.repository.AuthRepository
import uz.khusinov.karvon.domain.repository.OrdersRepository
import uz.khusinov.karvon.domain.use_case.auth.AuthUseCases
import uz.khusinov.karvon.domain.use_case.auth.ConfirmUseCase
import uz.khusinov.karvon.domain.use_case.auth.LoginUseCase
import uz.khusinov.karvon.domain.use_case.orders.GetOrdersUseCase
import uz.khusinov.karvon.domain.use_case.orders.OrdersUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            loginUseCase = LoginUseCase(repository),
            confirmUseCase = ConfirmUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideOrdersUseCase(repository: OrdersRepository): OrdersUseCases {
        return OrdersUseCases(
            getOrdersUseCase = GetOrdersUseCase(repository)
        )
    }


//    @Provides
//    @Singleton
//    fun provideProfileRepository(repository: ProfileRepository): ProfileUseCases {
//        return ProfileUseCases(
//            profileUseCase = ProfileUseCase(repository),
//            deleteAvatarUseCase = DeleteAvatarUseCase(repository),
//            updateAvatarUseCase = UpdateAvatarUseCase(repository),
//            setBalanceUseCase = SetBalanceUseCase(repository),
//            carInfoUseCase = CarInfoUseCase(repository),
//            logoutUseCase = LogoutUseCase(repository)
//        )
//    }
}