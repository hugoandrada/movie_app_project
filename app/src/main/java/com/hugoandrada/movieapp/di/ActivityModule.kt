package com.hugoandrada.movieapp.di

import com.hugoandrada.movieapp.data.remote.DataSourceImplement
import com.hugoandrada.movieapp.data.remote.MovieDataSource
import com.hugoandrada.movieapp.repo.local.RepoLocal
import com.hugoandrada.movieapp.repo.local.RepoLocalImp
import com.hugoandrada.movieapp.repo.remote.MovieRepository
import com.hugoandrada.movieapp.repo.remote.RepoImplement
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun bindRepoImpl(repoImplement: RepoImplement) : MovieRepository
    @Binds
    abstract fun bindRepoLocalImpl(repoLocalImp: RepoLocalImp) : RepoLocal
    @Binds
    abstract fun bindDataSourceImpl(dataSourceImplement: DataSourceImplement) : MovieDataSource
}