<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\SocialAccount;
use App\Models\User;

class SocialAccountSeeder extends Seeder
{
    public function run()
    {
        $users = User::all();

        foreach ($users as $user) {
            SocialAccount::factory(2)->create([
                'user_id' => $user->id,
                'provider_id' => (string) fake()->unique()->randomNumber(8)
            ]);
        }
    }
}
