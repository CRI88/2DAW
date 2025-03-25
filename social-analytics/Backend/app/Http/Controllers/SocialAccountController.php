<?php

namespace App\Http\Controllers;

use App\Models\SocialAccount;
use Illuminate\Http\Request;

class SocialAccountController extends Controller
{
    public function index()
    {
        return response()->json(SocialAccount::all());
    }

    public function show($id)
    {
        return response()->json(SocialAccount::findOrFail($id));
    }

    public function store(Request $request)
    {
        $account = SocialAccount::create($request->all());
        return response()->json($account, 201);
    }

    public function update(Request $request, $id)
    {
        $account = SocialAccount::findOrFail($id);
        $account->update($request->all());
        return response()->json($account);
    }

    public function destroy($id)
    {
        SocialAccount::destroy($id);
        return response()->json(null, 204);
    }
}
