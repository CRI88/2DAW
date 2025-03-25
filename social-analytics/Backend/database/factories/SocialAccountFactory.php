<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;
use App\Models\SocialAccount;
use App\Models\User;

class SocialAccountFactory extends Factory
{
    protected $model = SocialAccount::class;

    public function definition()
    {
        return [
            'user_id' => User::factory(),
            'provider' => $this->faker->randomElement(['twitter', 'facebook', 'twitter']),
            'provider_id' => (string) $this->faker->unique()->randomNumber(8),
        ];
    }
}
