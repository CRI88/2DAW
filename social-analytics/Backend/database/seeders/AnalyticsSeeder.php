<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Analytics;
use App\Models\User;

class AnalyticsSeeder extends Seeder
{
    public function run()
    {
        $users = User::all();

        foreach ($users as $user) {
            Analytics::factory()->create([
                'user_id' => $user->id
            ]);
        }
    }
}
