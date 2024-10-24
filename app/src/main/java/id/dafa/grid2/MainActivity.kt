package id.dafa.grid2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.dafa.grid2.Data.DataSource
import id.dafa.grid2.Model.Topic
import id.dafa.grid2.ui.theme.Grid2Theme

// Kelas utama aplikasi yang mewarisi ComponentActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Mengaktifkan tampilan edge-to-edge
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Mengatur konten tampilan menggunakan Jetpack Compose
        setContent {
            Grid2Theme {
                // Container surface menggunakan warna latar belakang dari tema
                Surface(
                    modifier = Modifier
                        .fillMaxSize() // Mengisi ukuran maksimum
                        .statusBarsPadding(), // Menambahkan padding untuk status bar
                    color = MaterialTheme.colorScheme.background // Menggunakan warna latar belakang tema
                ) {
                    // Menampilkan grid topik dengan padding
                    TopicGrid(
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small),
                        )
                    )
                }
            }
        }
    }
}

// Fungsi komposabel untuk menampilkan grid topik
@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    // Menggunakan LazyVerticalGrid untuk menampilkan item dalam bentuk grid
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Menentukan jumlah kolom
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak vertikal antar item
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // Jarak horizontal antar item
        modifier = modifier
    ) {
        // Mengambil daftar topik dari sumber data dan menampilkan setiap topik dalam TopicCard
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

// Fungsi komposabel untuk menampilkan setiap kartu topik
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        // Menggunakan Row untuk menampilkan gambar dan informasi topik secara horizontal
        Row {
            Box {
                // Menampilkan gambar topik
                Image(
                    painter = painterResource(id = topic.imageRes), // Mengambil gambar dari sumber daya
                    contentDescription = null, // Deskripsi konten gambar
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp) // Menentukan ukuran gambar
                        .aspectRatio(1f), // Menjaga rasio aspek gambar
                    contentScale = ContentScale.Crop // Memotong gambar agar sesuai
                )
            }

            // Menampilkan kolom informasi topik
            Column {
                Text(
                    text = stringResource(id = topic.name), // Mengambil nama topik dari sumber daya string
                    style = MaterialTheme.typography.bodyMedium, // Menentukan gaya teks
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Menampilkan ikon dan jumlah kursus yang tersedia
                    Icon(
                        painter = painterResource(R.drawable.ic_grain), // Ikon untuk kursus
                        contentDescription = null, // Deskripsi konten ikon
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = topic.availableCourses.toString(), // Menampilkan jumlah kursus sebagai teks
                        style = MaterialTheme.typography.labelMedium, // Menentukan gaya teks
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

// Fungsi preview untuk menampilkan contoh tampilan TopicCard
@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    Grid2Theme {
        // Membuat contoh topik untuk ditampilkan dalam preview
        val topic = Topic(R.string.photography, 321, R.drawable.img1)
        Column(
            modifier = Modifier.fillMaxSize(), // Mengisi ukuran maksimum
            verticalArrangement = Arrangement.Center, // Menempatkan konten di tengah secara vertikal
            horizontalAlignment = Alignment.CenterHorizontally // Menempatkan konten di tengah secara horizontal
        ) {
            TopicCard(topic = topic) // Menampilkan TopicCard dengan contoh topik
        }
    }
}
