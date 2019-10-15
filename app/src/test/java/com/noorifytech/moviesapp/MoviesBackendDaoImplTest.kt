package com.noorifytech.moviesapp

import com.noorifytech.moviesapp.common.AppException
import com.noorifytech.moviesapp.common.ErrorCodes
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.backend.dto.ApiErrorResponse
import com.noorifytech.moviesapp.data.dao.backend.dto.ApiResponse
import com.noorifytech.moviesapp.data.dao.backend.dto.MovieDto
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.data.dao.backend.impl.MoviesBackendDaoImpl
import com.noorifytech.moviesapp.data.dao.backend.impl.retrofit.TMDBApi
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.base.RxBaseProcessor
import com.noorifytech.moviesapp.ui.presenter.MoviesListPresenter
import com.noorifytech.moviesapp.ui.presenter.impl.MoviesListPresenterImpl
import com.noorifytech.moviesapp.ui.usecase.GetPopularMoviesUseCase
import com.noorifytech.moviesapp.ui.view.MoviesListView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.lang.Exception
import kotlin.system.exitProcess

class MoviesBackendDaoImplTest {

    @Mock
    lateinit var api: TMDBApi

    lateinit var moviesBackendDao: MoviesBackendDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesBackendDao = MoviesBackendDaoImpl(api)
    }

    @Test
    fun getPopularMovies_whenSuccessResponse_returnsMoviesListResponseInBody() {
        // Arrange
        val moviesListResponse = MoviesListResponse(
            page = 1,
            totalResults = 1,
            totalPages = 10,
            results = listOf(MovieDto(1, "Dark knight rises", "posterPath", 1, "Dark knight rises", "10-09-2019", 5f))
        )
        Mockito.`when`(api.getPopularMovies()).thenReturn(Observable.just(Response.success(moviesListResponse)))

        // Act
        val testObserver = moviesBackendDao.getPopularMovies().test()

        // Assert
        testObserver.assertValue(moviesListResponse)
    }

    @Test
    fun getPopularMovies_whenErrorResponse_returnsError() {

        // Arrange
        val errorException = AppException(ErrorCodes.BACKEND_ERROR, "error")
        Mockito.`when`(api.getPopularMovies())
            .thenReturn(Observable.just(Response.error(400, ResponseBody.create(null, "error"))))

        // Act
        val testObserver = moviesBackendDao.getPopularMovies().test()

        // Assert
        testObserver.assertError(errorException)
    }
}