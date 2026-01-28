package com.example.yakap.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

fun provideSupabaseClient() = createSupabaseClient(
    supabaseUrl = SupabaseConfig.URL,
    supabaseKey = SupabaseConfig.ANON_KEY
) {
    install(Auth)
    install(Postgrest)
    install(Realtime)
}
