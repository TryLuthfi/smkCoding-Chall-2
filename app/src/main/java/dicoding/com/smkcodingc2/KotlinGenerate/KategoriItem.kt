package dicoding.com.smkcodingc2.KotlinGenerate


import com.google.gson.annotations.SerializedName

data class KategoriItem(
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("id_kategori")
    val idKategori: Int,
    @SerializedName("nama")
    val nama: String
)