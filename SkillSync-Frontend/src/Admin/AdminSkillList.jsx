import React, { useEffect, useState } from 'react';
import API from '../api/api';

export default function AdminSkillList() {
  const [skills, setSkills] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const response = await API.get('/skills');
        setSkills(response.data);
      } catch (err) {
        console.error('Failed to fetch skills:', err);
        setError('Unable to load skills.');
      } finally {
        setLoading(false);
      }
    };

    fetchSkills();
  }, []);

  return (
    <div className="container my-5">
      <h2 className="text-center mb-4">All Skills (Admin View)</h2>

      {loading ? (
        <div className="text-center text-muted">Loading...</div>
      ) : error ? (
        <div className="alert alert-danger text-center">{error}</div>
      ) : skills.length === 0 ? (
        <div className="alert alert-info text-center">No skills found.</div>
      ) : (
        <div className="d-flex flex-wrap justify-content-start">
          {skills.map((skill) => (
            <div key={skill.id} className="card p-3 m-2 shadow-sm" style={{ width: '300px' }}>
              <h5>{skill.name}</h5>
              <p><strong>ID:</strong> {skill.id}</p>
              <p><strong>Category:</strong> {skill.category || 'N/A'}</p>
              <p><strong>Description:</strong> {skill.description || 'N/A'}</p>
              <p><strong>Level:</strong> {skill.level}</p>
              <p><strong>Estimated Time:</strong> {skill.estimatedTime || 'N/A'}</p>
              <p><strong>Created By:</strong> {skill.creatorName || 'N/A'}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
