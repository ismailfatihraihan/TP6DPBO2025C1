# Janji
Saya Ismail Fatih Raihan dengan NIM 2307840 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin. 

# Flappy Bird Game - Java Swing

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue)

Sebuah clone sederhana dari game Flappy Bird menggunakan Java Swing GUI. Game ini dikembangkan sebagai bagian dari praktikum Desain dan Pemrograman Berorientasi Objek.

---

## 📋 Deskripsi
Game ini menirukan mekanik dasar Flappy Bird, di mana pemain mengontrol burung untuk melewati pipa-pipa penghalang. Fitur yang diimplementasikan:
- **Main Menu** (opsional bonus)
- Gerakan gravitasi dan lompatan
- Deteksi tabrakan dengan pipa atau tanah
- Penghitungan skor
- Layar "Game Over" dengan opsi restart
- Restart permainan dengan tombol `R`

---

## 📷 Dokumentasi
![play](https://github.com/user-attachments/assets/fd7a3fd8-2646-4ba9-92cf-71c677fb85fd)

---

## 🎮 Cara Bermain
1. **Mulai Permainan**: Tekan `Spasi` untuk melompat.
2. **Hindari Pipa**: Terbang melalui celah antara pipa atas dan bawah.
3. **Skor**: Setiap pipa yang berhasil dilewati memberi +1 skor.
4. **Game Over**:
   - Jika menabrak pipa atau jatuh ke tanah.
   - Tekan `R` untuk restart.

---

## 🛠 Struktur Program
### Kelas Utama
| File           | Deskripsi                                                                 |
|----------------|---------------------------------------------------------------------------|
| `App.java`     | Menjalankan game atau menampilkan Main Menu.                             |
| `MainMenu.java`| GUI menu awal (opsional bonus).                                          |
| `FlappyBird.java` | Panel game utama: logika permainan, rendering, dan input keyboard.    |
| `Player.java`  | Merepresentasikan objek pemain (burung).                                 |
| `Pipe.java`    | Merepresentasikan pipa penghalang.                                       |

### Alur Program
```mermaid
graph TD
  A[Main Menu] -->|Start Game| B[FlappyBird JFrame]
  B --> C{Game Loop}
  C -->|Update| D[Gerakan Pipa & Player]
  C -->|Render| E[Gambar Background, Pipa, Player]
  D --> F[Deteksi Tabrakan?]
  F -->|Ya| G[Tampilkan Game Over]
  F -->|Tidak| C
  G -->|Tekan R| B
