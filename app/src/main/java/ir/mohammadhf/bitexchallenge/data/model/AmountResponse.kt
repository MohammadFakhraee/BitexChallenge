package ir.mohammadhf.bitexchallenge.data.model

import com.squareup.moshi.Json

data class AmountResponse(
	@Json(name="token")
	val token: String? = null,

	@Json(name="amount")
	val amount: Double? = null,

	@Json(name="created_at")
	val createdAt: String? = null,

	@Json(name="id")
	val id: String? = null,
)

data class AmountResponseUi(
	val amount: Double,

	val createdAt: String,

	val id: String,

	val token: String
)
