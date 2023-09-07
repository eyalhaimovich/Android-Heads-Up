package com.phamspect.headsupgame

import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.OpenAI
import kotlin.time.Duration.Companion.seconds

class AiTest{
    val openai = OpenAI(
        token = "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",
        timeout = Timeout(socket = 60.seconds),
    )
}