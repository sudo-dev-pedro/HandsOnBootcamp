package com.example.testapplication.di

import com.example.testapplication.Car
import com.example.testapplication.Engine
import com.example.testapplication.TrafficSystem
import org.koin.dsl.module

val appModule = module {
//    single { Engine() }
    single { Car(engine = get()) }

    single { TrafficSystem() }
}
