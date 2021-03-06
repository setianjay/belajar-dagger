package com.techyourchance.dagger2course.common.composition.dependencyinjection

import com.techyourchance.dagger2course.common.composition.PresentationCompositionRoot
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import java.lang.Exception
import java.lang.reflect.Field

/**
 * this class injecting service for client needed
 * */
class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(client: Any){
        for (field in getAllField(client)){
            if (isServiceAnnotation(field)){
                injectedField(client, field)
            }
        }
    }

    private fun getAllField(client: Any): Array<Field>{
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    private fun isServiceAnnotation(field: Field): Boolean{
        for (annotation in field.annotations){
            if (annotation is Service){
                return true
            }
        }
        return false
    }

    private fun injectedField(client: Any, field: Field){
        val initiallyAccessible = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceClasses(field.type))
        field.isAccessible = initiallyAccessible
    }

    private fun getServiceClasses(type: Any): Any{
        return when (type){
            DialogsNavigator::class.java -> compositionRoot.dialogsNavigator
            FetchQuestionsUseCase::class.java -> compositionRoot.fetchQuestionsUseCase
            ScreensNavigator::class.java -> compositionRoot.screensNavigator
            ViewMvcFactory::class.java -> compositionRoot.viewMvcFactory
            else -> throw Exception("unsupported service classes type : $type")
        }
    }

}