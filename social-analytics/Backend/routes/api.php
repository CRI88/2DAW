<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

use App\Http\Controllers\UserController;
use App\Http\Controllers\SocialAccountController;
use App\Http\Controllers\PostController;
use App\Http\Controllers\AnalyticsController;

Route::middleware('api')->group(function () {
    // Rutas de usuarios
    Route::get('/users', [UserController::class, 'index']);
    Route::get('/users/{id}', [UserController::class, 'show']);
    Route::post('/users', [UserController::class, 'store']);
    Route::put('/users/{id}', [UserController::class, 'update']);
    Route::delete('/users/{id}', [UserController::class, 'destroy']);

    // Rutas de cuentas sociales
    Route::get('/social-accounts', [SocialAccountController::class, 'index']);
    Route::get('/social-accounts/{id}', [SocialAccountController::class, 'show']);
    Route::post('/social-accounts', [SocialAccountController::class, 'store']);
    Route::put('/social-accounts/{id}', [SocialAccountController::class, 'update']);
    Route::delete('/social-accounts/{id}', [SocialAccountController::class, 'destroy']);

    // Rutas de posts
    Route::get('/posts', [PostController::class, 'index']);
    Route::get('/posts/{id}', [PostController::class, 'show']);
    Route::post('/posts', [PostController::class, 'store']);
    Route::put('/posts/{id}', [PostController::class, 'update']);
    Route::delete('/posts/{id}', [PostController::class, 'destroy']);

    // Rutas de estadísticas
    Route::get('/analytics', [AnalyticsController::class, 'index']);

    Route::get('/test', function () {
        return response()->json(['message' => 'API funcionando correctamente']);
    });
    
});
