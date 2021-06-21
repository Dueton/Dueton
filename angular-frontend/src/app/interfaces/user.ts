export interface User {
    userId: string;
    username: string;
    profilePictureUrl: string;

    firstName: string;
    lastName: string;

    lastSearchedSongId: number;
    voteCount: number;
}