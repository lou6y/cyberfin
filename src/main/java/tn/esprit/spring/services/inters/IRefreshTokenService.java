package tn.esprit.spring.services.inters;

import java.util.Optional;

import tn.esprit.spring.dao.entities.RefreshToken;

public interface IRefreshTokenService {
	public Optional<RefreshToken> findByToken(String token);
	public RefreshToken createRefreshToken(Long userId);
	public RefreshToken verifyExpiration(RefreshToken token);
	public int deleteByUserId(Long userId);

}
