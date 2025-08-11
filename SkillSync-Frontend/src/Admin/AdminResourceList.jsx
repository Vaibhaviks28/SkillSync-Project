import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Card, Container, Row, Col, Spinner, Alert } from 'react-bootstrap';

const AdminResourceList = () => {
  const [resources, setResources] = useState([]);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState('');
  const [variant, setVariant] = useState('danger');

  const fetchResources = () => {
    setLoading(true);
    setMessage('');
    axios.get('http://localhost:8080/api/resources')
      .then(response => {
        setResources(response.data);
        setLoading(false);
      })
      .catch(error => {
        setMessage('Failed to load resources');
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchResources();
  }, []);

  // Helper to get skill info safely
  const renderSkillInfo = (resource) => {
    if (resource.skill) {
      return `${resource.skill.name} (ID: ${resource.skill.id})`;
    }
    return 'No skill info';
  };

  return (
    <Container className="mt-4">
      <h2 className="mb-4 text-center">All Resources (Admin View)</h2>
      {loading && <Spinner animation="border" />}
      {message && <Alert variant={variant}>{message}</Alert>}
      <Row>
        {resources.map(resource => (
          <Col key={resource.id} md={4}>
            <Card className="mb-3">
              <Card.Body>
                <Card.Title>{resource.title}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">Type: {resource.type}</Card.Subtitle>
                <Card.Text>Skill: {renderSkillInfo(resource)}</Card.Text>
                {resource.url ? (
                  <Card.Link href={resource.url.startsWith('http') ? resource.url : `http://localhost:8080${resource.url}`} target="_blank" rel="noopener noreferrer">
                    Visit Resource
                  </Card.Link>
                ) : (
                  <Card.Text>No URL available</Card.Text>
                )}
              </Card.Body>
            </Card>
          </Col>
        ))}
        {!loading && resources.length === 0 && (
          <Col>
            <Alert variant="info">No resources found.</Alert>
          </Col>
        )}
      </Row>
    </Container>
  );
};

export default AdminResourceList;
