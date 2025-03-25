<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Post;

class AnalyticsController extends Controller
{
    public function index()
    {
        // Simulación de métricas generales de interacción en redes sociales
        $data = [
            'total_posts' => Post::count(),
            'average_likes' => Post::average('likes') ?? 0,
            'average_comments' => Post::average('comments') ?? 0,
        ];
        
        return response()->json($data);
    }
}
