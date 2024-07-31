package uz.khusinov.karvon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.khusinov.karvon.data.remote.ApiService
import uz.khusinov.karvon.data.repository.AuthRepositoryImpl
import uz.khusinov.karvon.data.repository.OrdersRepositoryImpl
import uz.khusinov.karvon.domain.repository.AuthRepository
import uz.khusinov.karvon.domain.repository.OrdersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideOrdersRepository(apiService: ApiService): OrdersRepository {
        return OrdersRepositoryImpl(apiService)
    }
//
//    @Provides
//    @Singleton
//    fun provideProfileRepository(apiService: ApiService): ProfileRepository {
//        return ProfileRepositoryImpl(apiService)
//    }
}