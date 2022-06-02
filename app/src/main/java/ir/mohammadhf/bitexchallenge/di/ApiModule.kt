package ir.mohammadhf.bitexchallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.mohammadhf.bitexchallenge.data.source.AmountApiDataSource
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://6294760fa7203b3ed06971f5.mockapi.io/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideUserApiDataSource(retrofit: Retrofit): AmountApiDataSource =
        retrofit.create(AmountApiDataSource::class.java)
}