package dicoding.com.smkcodingc2.KotlinGenerate


import com.google.gson.annotations.SerializedName

data class WisataItem(
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("id_kategori")
    val idKategori: Int,
    @SerializedName("id_wisata")
    val idWisata: Int,
    @SerializedName("lokasi")
    val lokasi: String,
    @SerializedName("nama")
    val nama: String


)