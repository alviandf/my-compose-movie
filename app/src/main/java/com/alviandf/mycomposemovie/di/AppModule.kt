package com.alviandf.mycomposemovie.di

import androidx.room.Room
import com.alviandf.mycomposemovie.BuildConfig
import com.alviandf.mycomposemovie.data.MovieRepository
import com.alviandf.mycomposemovie.data.MovieRepositoryImpl
import com.alviandf.mycomposemovie.data.local.LocalDataSource
import com.alviandf.mycomposemovie.data.local.database.MovieDatabase
import com.alviandf.mycomposemovie.data.remote.RemoteDataSource
import com.alviandf.mycomposemovie.data.remote.api.ApiClient
import com.alviandf.mycomposemovie.domain.MovieInteractor
import com.alviandf.mycomposemovie.domain.MovieUseCase
import com.alviandf.mycomposemovie.ui.viewmodel.MovieViewModel
import com.alviandf.mycomposemovie.utils.Constants.BASE_URL
import com.alviandf.mycomposemovie.utils.Constants.DB_KEY
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(DB_KEY.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movie.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else HttpLoggingInterceptor.Level.NONE
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiClient::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}