-- ============================================
-- JSpotify PostgreSQL Database
-- ============================================

-- Connect to jspotify before running this file.
-- In psql:
-- \c jspotify


-- ============================================
-- ENUM TYPES
-- ============================================

CREATE TYPE user_role AS ENUM (
    'USER',
    'ADMIN'
);


-- ============================================
-- USERS
-- ============================================

CREATE TABLE IF NOT EXISTS users (

    id BIGSERIAL PRIMARY KEY,

    username VARCHAR(50) NOT NULL UNIQUE,

    email VARCHAR(150) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    profile_image VARCHAR(500),

    role user_role NOT NULL DEFAULT 'USER',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- ============================================
-- ARTISTS
-- ============================================

CREATE TABLE IF NOT EXISTS artists (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(150) NOT NULL,

    bio TEXT,

    image_path VARCHAR(500),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- ============================================
-- ALBUMS
-- ============================================

CREATE TABLE IF NOT EXISTS albums (

    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(200) NOT NULL,

    artist_id BIGINT NOT NULL,

    cover_path VARCHAR(500),

    release_date DATE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_album_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id)
        ON DELETE CASCADE
);


-- ============================================
-- SONGS
-- ============================================

CREATE TABLE IF NOT EXISTS songs (

    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(200) NOT NULL,

    artist_id BIGINT NOT NULL,

    album_id BIGINT,

    duration_seconds INTEGER NOT NULL DEFAULT 0,

    audio_path VARCHAR(500) NOT NULL,

    cover_path VARCHAR(500),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_song_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_song_album
        FOREIGN KEY (album_id)
        REFERENCES albums(id)
        ON DELETE SET NULL
);


-- ============================================
-- PLAYLISTS
-- ============================================

CREATE TABLE IF NOT EXISTS playlists (

    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,

    name VARCHAR(200) NOT NULL,

    description TEXT,

    cover_path VARCHAR(500),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_playlist_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);


-- ============================================
-- PLAYLIST SONGS
-- ============================================

CREATE TABLE IF NOT EXISTS playlist_songs (

    playlist_id BIGINT NOT NULL,

    song_id BIGINT NOT NULL,

    position INTEGER NOT NULL DEFAULT 0,

    added_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (playlist_id, song_id),

    CONSTRAINT fk_playlist_song_playlist
        FOREIGN KEY (playlist_id)
        REFERENCES playlists(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_playlist_song_song
        FOREIGN KEY (song_id)
        REFERENCES songs(id)
        ON DELETE CASCADE
);


-- ============================================
-- LIKED SONGS
-- ============================================

CREATE TABLE IF NOT EXISTS liked_songs (

    user_id BIGINT NOT NULL,

    song_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (user_id, song_id),

    CONSTRAINT fk_liked_song_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_liked_song_song
        FOREIGN KEY (song_id)
        REFERENCES songs(id)
        ON DELETE CASCADE
);


-- ============================================
-- FOLLOWING
-- ============================================

CREATE TABLE IF NOT EXISTS following (

    user_id BIGINT NOT NULL,

    artist_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (user_id, artist_id),

    CONSTRAINT fk_following_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_following_artist
        FOREIGN KEY (artist_id)
        REFERENCES artists(id)
        ON DELETE CASCADE
);


-- ============================================
-- LISTENING HISTORY
-- ============================================

CREATE TABLE IF NOT EXISTS listening_history (

    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,

    song_id BIGINT NOT NULL,

    played_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_history_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_history_song
        FOREIGN KEY (song_id)
        REFERENCES songs(id)
        ON DELETE CASCADE
);