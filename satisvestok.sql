-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 02 Haz 2020, 23:31:02
-- Sunucu sürümü: 10.4.11-MariaDB
-- PHP Sürümü: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `satisvestok`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `accounts`
--

CREATE TABLE `accounts` (
  `Id` int(11) NOT NULL,
  `YetkiId` int(11) NOT NULL,
  `PersonelId` int(11) NOT NULL,
  `Sifre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `accounts`
--

INSERT INTO `accounts` (`Id`, `YetkiId`, `PersonelId`, `Sifre`) VALUES
(1, 1, 1, '123'),
(7, 2, 5, '123'),
(8, 3, 6, '123'),
(9, 8, 9, '123'),
(10, 8, 9, 'asd'),
(11, 5, 9, '123'),
(12, 8, 9, '123'),
(13, 8, 10, 'asd');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hesap`
--

CREATE TABLE `hesap` (
  `Id` int(11) NOT NULL,
  `Adet` int(11) NOT NULL,
  `Fiyat` int(11) NOT NULL,
  `UrunId` int(11) NOT NULL,
  `masaNo` int(11) NOT NULL,
  `toplamFiyat` int(11) NOT NULL,
  `Tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `hesap`
--

INSERT INTO `hesap` (`Id`, `Adet`, `Fiyat`, `UrunId`, `masaNo`, `toplamFiyat`, `Tarih`) VALUES
(143, 1, 14, 8, 1, 14, '2020-05-02'),
(144, 12, 14, 8, 1, 168, '2020-05-02'),
(155, 12, 1, 6, 6, 12, '2020-05-09'),
(168, 5, 3, 8, 5, 15, '2020-05-22'),
(170, 5, 3, 8, 3, 15, '2020-05-16'),
(171, 5, 3, 8, 6, 15, '2020-05-16'),
(172, 2, 3, 8, 5, 6, '2020-05-15');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategori`
--

CREATE TABLE `kategori` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) NOT NULL,
  `ParentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `kategori`
--

INSERT INTO `kategori` (`Id`, `Adi`, `ParentId`) VALUES
(5, 'Meyve Suyu Çeşitleri', 0),
(7, 'Çay Çeşitleri', 7),
(8, 'Kahve Çeşitleri', 8),
(13, 'Soğuk İçecekler', 0),
(14, 'berat', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `musteri`
--

CREATE TABLE `musteri` (
  `id` int(11) NOT NULL,
  `AdiSoyadi` varchar(255) NOT NULL,
  `Telefon` varchar(255) NOT NULL,
  `Adres` varchar(255) NOT NULL,
  `SehirId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `musteri`
--

INSERT INTO `musteri` (`id`, `AdiSoyadi`, `Telefon`, `Adres`, `SehirId`) VALUES
(1, 'kars', '36', '36', 4),
(3, 'abdurrahim', '123123', 'dasdasd', 0),
(4, 'erol', '4234234', 'kamil ocak', 0),
(5, 'asddsasdasda', '12312321', 'asdsdadsa', 0),
(6, 'asddsasdasdaasdsadas1', '12312321', 'asdsdadsa', 2),
(7, 'rahimgng', '5419741718', 'kamil ocak', 1),
(8, 'a', '2313', 'a', 1),
(9, 'asd', '213213', 'anlara', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personel`
--

CREATE TABLE `personel` (
  `Id` int(11) NOT NULL,
  `AdiSoyadi` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `personel`
--

INSERT INTO `personel` (`Id`, `AdiSoyadi`, `Email`) VALUES
(1, 'Abdurrahim Güngör', 'abdurrahimgungorr@gmail.com'),
(5, 'Patron', 'patron@gmail.com'),
(6, 'Müşteri', 'musteri@gmail.com'),
(9, 'boş', 'boş'),
(10, 'asd', 'asd');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `satis`
--

CREATE TABLE `satis` (
  `Id` int(11) NOT NULL,
  `UrunId` int(11) NOT NULL,
  `MusteriId` int(11) NOT NULL,
  `PersonelId` int(11) NOT NULL,
  `Adet` int(11) NOT NULL,
  `Tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `satis`
--

INSERT INTO `satis` (`Id`, `UrunId`, `MusteriId`, `PersonelId`, `Adet`, `Tarih`) VALUES
(1, 6, 4, 1, 3, '2020-05-24'),
(2, 7, 3, 1, 1, '2020-05-09'),
(3, 7, 3, 1, 1, '2020-05-02'),
(4, 6, 4, 1, 213, '2020-05-22'),
(5, 9, 1, 1, 1, '2020-05-09'),
(6, 9, 1, 1, 3, '2020-05-09'),
(7, 6, 1, 1, 1, '2020-05-15'),
(8, 8, 1, 1, 2, '2020-05-14'),
(9, 6, 1, 1, 12, '2020-05-06'),
(10, 6, 1, 1, 2, '2020-05-09'),
(11, 6, 3, 1, 3, '2020-05-09'),
(12, 6, 3, 1, 12, '2020-05-13'),
(13, 6, 1, 1, 13, '2020-05-07'),
(14, 6, 1, 1, 14, '2020-05-14'),
(15, 6, 1, 1, 12, '2020-05-12'),
(16, 6, 1, 1, 12, '2020-05-15'),
(17, 6, 1, 1, 12, '2020-05-08'),
(18, 6, 3, 1, 10, '2020-05-13'),
(19, 6, 1, 1, 12, '2020-05-07'),
(20, 6, 1, 1, 12, '2020-05-13'),
(21, 6, 1, 1, 11, '2020-05-07'),
(22, 6, 1, 1, 12, '2020-05-21'),
(23, 6, 1, 1, 11, '2020-05-21'),
(24, 6, 1, 1, 12, '2020-05-07'),
(25, 6, 1, 1, 12, '2020-05-09'),
(26, 6, 1, 1, 12, '2020-05-21'),
(27, 9, 1, 1, 12, '2020-05-08'),
(28, 9, 1, 1, 12, '2020-05-27'),
(29, 9, 1, 1, 26, '2020-05-27'),
(30, 6, 1, 1, 213, '2020-05-14'),
(31, 6, 1, 1, 50, '2020-05-19'),
(32, 6, 1, 1, 50, '2020-05-11'),
(33, 6, 1, 1, 44, '2020-05-11'),
(34, 6, 1, 1, 12321, '2020-05-22'),
(35, 6, 1, 1, 2, '2020-05-15'),
(36, 6, 1, 1, 90, '2020-05-20');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `sehir`
--

CREATE TABLE `sehir` (
  `sehirId` int(11) NOT NULL,
  `sehirAdi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `sehir`
--

INSERT INTO `sehir` (`sehirId`, `sehirAdi`) VALUES
(1, 'Ankara'),
(2, 'İstanbul'),
(4, 'Kars'),
(5, 'Zonguldak');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `stok`
--

CREATE TABLE `stok` (
  `Id` int(11) NOT NULL,
  `UrunId` int(11) NOT NULL,
  `PersonelId` int(11) NOT NULL,
  `Adet` int(11) NOT NULL,
  `Tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `stok`
--

INSERT INTO `stok` (`Id`, `UrunId`, `PersonelId`, `Adet`, `Tarih`) VALUES
(4, 6, 1, 0, '2020-05-24'),
(8, 7, 1, 25, '2020-05-24'),
(9, 8, 1, 36, '2020-05-16'),
(14, 9, 1, 156, '2020-05-09'),
(22, 10, 1, 123, '2020-05-02');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) NOT NULL,
  `KategoriId` int(11) NOT NULL,
  `Fiyat` decimal(10,0) NOT NULL,
  `Tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`Id`, `Adi`, `KategoriId`, `Fiyat`, `Tarih`) VALUES
(6, 'Yeşil Çay', 6, '3', '2020-05-25'),
(8, 'Soğuk Kahve', 8, '3', '2020-05-25'),
(9, 'tost', 8, '213', '2020-05-15'),
(10, 'Berat', 5, '12', '2020-05-12'),
(11, 'Berat', 5, '12', '2020-05-12');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yetkiler`
--

CREATE TABLE `yetkiler` (
  `Id` int(11) NOT NULL,
  `Adi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `yetkiler`
--

INSERT INTO `yetkiler` (`Id`, `Adi`) VALUES
(1, 'Admin'),
(4, 'Personel'),
(5, 'Müşteri'),
(8, 'boş');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `hesap`
--
ALTER TABLE `hesap`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `musteri`
--
ALTER TABLE `musteri`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `personel`
--
ALTER TABLE `personel`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `satis`
--
ALTER TABLE `satis`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `sehir`
--
ALTER TABLE `sehir`
  ADD PRIMARY KEY (`sehirId`);

--
-- Tablo için indeksler `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`Id`);

--
-- Tablo için indeksler `yetkiler`
--
ALTER TABLE `yetkiler`
  ADD PRIMARY KEY (`Id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `accounts`
--
ALTER TABLE `accounts`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `hesap`
--
ALTER TABLE `hesap`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=173;

--
-- Tablo için AUTO_INCREMENT değeri `kategori`
--
ALTER TABLE `kategori`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Tablo için AUTO_INCREMENT değeri `musteri`
--
ALTER TABLE `musteri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `personel`
--
ALTER TABLE `personel`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `satis`
--
ALTER TABLE `satis`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Tablo için AUTO_INCREMENT değeri `sehir`
--
ALTER TABLE `sehir`
  MODIFY `sehirId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `stok`
--
ALTER TABLE `stok`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `yetkiler`
--
ALTER TABLE `yetkiler`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
