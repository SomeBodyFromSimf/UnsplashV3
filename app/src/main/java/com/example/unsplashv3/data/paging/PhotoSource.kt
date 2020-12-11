package com.example.unsplashv3.data.paging

import androidx.paging.PagingSource
import com.example.unsplashv3.data.repo.Repository
import com.example.unsplashv3.model.Photo
import com.example.unsplashv3.model.PhotoWithLike

class PhotoSource(
    private val repository: Repository
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getPhotos(nextPage)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}